package tda.darkarmy.medicalecommerce.service;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tda.darkarmy.medicalecommerce.dto.ProductDto;
import tda.darkarmy.medicalecommerce.exception.ResourceNotFoundException;
import tda.darkarmy.medicalecommerce.model.CartItem;
import tda.darkarmy.medicalecommerce.model.OrderItem;
import tda.darkarmy.medicalecommerce.model.Product;
import tda.darkarmy.medicalecommerce.model.User;
import tda.darkarmy.medicalecommerce.repository.CartItemRepository;
import tda.darkarmy.medicalecommerce.repository.OrderItemRepository;
import tda.darkarmy.medicalecommerce.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final String BASE_URL = "http://localhost:8000";
    private Path fileStoragePath;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public ProductService() {
        try {
            fileStoragePath = Paths.get("src\\main\\resources\\static\\fileStorage").toAbsolutePath().normalize();
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> getAllApproved(){
        return productRepository.findAll().stream().filter(product -> product.getApproved()).collect(Collectors.toList());
    }

    public List<Product> findByProducts(){
        User user = userService.getLoggedInUser();
        return productRepository.findByUser(user);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    public Product addProduct(ProductDto productDto){
        String fileName = 
        		StringUtils.cleanPath(Objects.requireNonNull(productDto.getImage().getOriginalFilename()));
        System.out.println(fileName);
        fileName = fileName.replace(" ", "");
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(productDto.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product product = modelMapper.map(productDto, Product.class);
        product.setUser(userService.getLoggedInUser());
        product.setImageUrl(BASE_URL + "/fileStorage/" + fileName);
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductDto productDto){
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        Product product1 = modelMapper.map(productDto, Product.class);
        product1.setUser(userService.getLoggedInUser());
        product1.setId(productId);
        product1.setApproved(true);
        product1.setImageUrl(product.getImageUrl());
        return productRepository.save(product1);
    }

    public Product approveProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        product.setApproved(true);
        return productRepository.save(product);
    }

    public String deleteProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        List<OrderItem> orderItem = orderItemRepository.findByProduct(product);
        List<CartItem> cartItem = cartItemRepository.findByProduct(product);
        orderItem.stream().forEach(orderItem1 -> orderItemRepository.deleteById(orderItem1.getId()));
        cartItem.stream().forEach(cartItem1 -> cartItemRepository.deleteById(cartItem1.getId()));
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }
}
