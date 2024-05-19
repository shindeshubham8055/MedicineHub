package tda.darkarmy.medicalecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.medicalecommerce.dto.ChangeOrderStatusRequestDto;
import tda.darkarmy.medicalecommerce.dto.OrderItemDto;
import tda.darkarmy.medicalecommerce.exception.ResourceNotFoundException;
import tda.darkarmy.medicalecommerce.model.*;
import tda.darkarmy.medicalecommerce.repository.*;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<Orders> getMyOrders(){
        return orderRepository.findByUser(userService.getLoggedInUser());
    }

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Orders placeOrder(OrderItemDto[] orderDto){
        User user = userService.getLoggedInUser();
        Orders orders = orderRepository.save(new Orders(user));

        Cart cart = cartRepository.findByUser(user);
        Arrays.asList(orderDto).stream().forEach(orderItem-> {
            Product product = productRepository.findById(orderItem.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
           // cartItemRepository.deleteByProductAndCart(product, cart);
            int quantity = orderItem.getQuantity()<=product.getStock()? orderItem.getQuantity() : product.getStock();
            product.setStock(product.getStock()-quantity);
            //addition by paddy for total price
//            orders.setTotalPrice(product.getPrice()*quantity);
            //
            productRepository.save(product);
            orderItemRepository.save(new OrderItem(product, quantity, orders));
        });

        return orderRepository.findById(orders.getId()).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
    }

    public OrderItem changeStatus(Long orderId, ChangeOrderStatusRequestDto orderStatusRequestDto){
        OrderItem orderItem = orderItemRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        orderItem.setStatus(orderStatusRequestDto.getStatus());
        Product product = productRepository.findById(orderItem.getProduct().getId()).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        if(orderStatusRequestDto.getStatus().equalsIgnoreCase("cancelled")){
            product.setStock(product.getStock()+orderItem.getQuantity());
            productRepository.save(product);
        }
        return orderItemRepository.save(orderItem);
    }
}
