package com.product.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
//	@Autowired
//    private SequenceGeneratorService sequenceGeneratorService;

	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
    	Date now = new Date();
    	
    	if (product.get_id() == null || product.get_id().isEmpty()) {
            throw new IllegalArgumentException("Product ID must be provided and cannot be empty.");
        }
    	
    	Optional<Product> existingProductOptional = productRepository.findById(product.get_id());

    	if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            product.setCreatedAt(existingProduct.getCreatedAt());
        } else {
            // Set the createdAt date only on creation
            product.setCreatedAt(now);
        }

    	
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
