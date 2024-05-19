package tda.darkarmy.medicalecommerce.service;

import org.modelmapper.internal.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.medicalecommerce.dto.CartItemDto;
import tda.darkarmy.medicalecommerce.exception.ResourceNotFoundException;
import tda.darkarmy.medicalecommerce.model.Cart;
import tda.darkarmy.medicalecommerce.model.CartItem;
import tda.darkarmy.medicalecommerce.model.Product;
import tda.darkarmy.medicalecommerce.model.User;
import tda.darkarmy.medicalecommerce.repository.CartItemRepository;
import tda.darkarmy.medicalecommerce.repository.CartRepository;
import tda.darkarmy.medicalecommerce.repository.ProductRepository;

import java.util.Arrays;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    public Cart getMyCart(){
        User user = userService.getLoggedInUser();
        return cartRepository.findByUser(user);
    }

    public Cart addToCart(CartItemDto cartItemDto){
        User user = userService.getLoggedInUser();
        Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow(()-> new TypePool.Resolution.NoSuchTypeException("Product not found"));
        Cart cart = cartRepository.findByUser(user);
        if(cart==null) {
            cart = cartRepository.save(new Cart(Arrays.asList(), user, 0));
        }
        CartItem cartItem = cartItemRepository.save(new CartItem(product,cart, cartItemDto.getQuantity()));

        cart.setTotalPrice(cart.getTotalPrice()+product.getPrice()*cartItem.getQuantity());
        cart = cartRepository.save(cart);
        return cart;
    }

    public Cart deleteFromCart(Long cartItemId){
        User user = userService.getLoggedInUser();
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new ResourceNotFoundException("Cart Item not found"));
        Cart cart = cartRepository.findByUser(user);
        cart.setTotalPrice(cart.getTotalPrice()-cartItem.getProduct().getPrice()+cartItem.getQuantity());
        cartItemRepository.deleteById(cartItemId);
        return cartRepository.save(cart);
    }

}
