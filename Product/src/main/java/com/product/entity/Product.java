package com.product.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.product.constant.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
@Document(collection = constant.products)
public class Product {
	
	@Id
    private String _id;

    private String name;
    private String image;
    private String description;
    private String brand;
    private String category;
    private double price;
    private int countInStock;
    private double rating;
    private int numReviews;

    @Field(constant.reviews)
    private List<String> reviews;

    private Date createdAt;

    @LastModifiedDate
    @Field(constant.updatedAt)
    private Date updatedAt;

}
