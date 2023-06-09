package com.sergio.jwt.backend.entites.cloth;

import com.sergio.jwt.backend.dtos.cloth.ProductDto;
import com.sergio.jwt.backend.entites.ProductList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 64, updatable = false, nullable = false)
    private String id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String imageLink;

    @Column(nullable=false)
    private int price;

    @Column(nullable=false)
    private int count;

    public Product(String name, String imageLink, int price, int count, Category category) {
        this.name = name;
        this.imageLink = imageLink;
        this.price = price;
        this.count = count;
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<ProductList> productLists = new ArrayList<>();

    public Product(ProductDto productDto, Category category){
        this.name = productDto.getName();
        this.imageLink = productDto.getImageLink();
        this.price = productDto.getPrice();
        this.count = productDto.getCount();
        this.category = category;
    }
}