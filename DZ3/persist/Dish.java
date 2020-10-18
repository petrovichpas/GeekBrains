package ru.geekbrains.persist;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private BigDecimal price;

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
