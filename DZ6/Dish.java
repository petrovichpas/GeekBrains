package geek.persist;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private BigDecimal price;

    @Column()
    private String picture;

    public Dish(String name, BigDecimal price, String picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }
}
