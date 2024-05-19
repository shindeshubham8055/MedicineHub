package tda.darkarmy.medicalecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.medicalecommerce.dto.ProductDto;
import tda.darkarmy.medicalecommerce.service.ProductService;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return status(200).body(productService.getAll());
    }

    @GetMapping("/approved")
    public ResponseEntity<?> getAllApproved(){
        return status(200).body(productService.getAllApproved());
    }

    @GetMapping("/my-products")
    public ResponseEntity<?> findMyProducts(){
        return status(200).body(productService.findByProducts());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long productId){
        return status(200).body(productService.findById(productId));
    }

    @PostMapping()
    public ResponseEntity<?> addProduct(@Valid @ModelAttribute ProductDto productDto){
        return status(201).body(productService.addProduct(productDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        return status(200).body(productService.updateProduct(productId, productDto));
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveProduct(@PathVariable("id") Long productId){
        return status(200).body(productService.approveProduct(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long productId){
        return status(200).body(productService.deleteProduct(productId));
    }
}
