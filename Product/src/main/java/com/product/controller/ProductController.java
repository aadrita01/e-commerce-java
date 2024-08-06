package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.constant.constant;
import com.product.entity.Product;
import com.product.service.ProductService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(constant.BASE_URL)
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(constant.BY_ID)
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping(constant.BY_ID)
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        Product product = productService.getProductById(id);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setImage(productDetails.getImage());
            product.setDescription(productDetails.getDescription());
            product.setBrand(productDetails.getBrand());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());
            product.setCountInStock(productDetails.getCountInStock());
            product.setRating(productDetails.getRating());
            product.setNumReviews(productDetails.getNumReviews());
            product.setReviews(productDetails.getReviews());
            productService.saveProduct(product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(constant.BY_ID)
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
