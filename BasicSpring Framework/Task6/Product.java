package ru.geekbrains.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Table(name = "goods_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    public Product(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }
}
