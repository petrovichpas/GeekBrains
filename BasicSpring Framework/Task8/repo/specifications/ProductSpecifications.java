package ru.geekbrains.springbootlesson.repo.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.springbootlesson.entity.Product;
import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterThanOrEqual(BigDecimal price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessThanOrEqual(BigDecimal price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> findByTitle(String title) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }
}
