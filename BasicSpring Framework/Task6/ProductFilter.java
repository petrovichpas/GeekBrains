package ru.geekbrains.service;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.specifications.ProductSpecifications;

import java.math.BigDecimal;
import java.util.Map;

public class ProductFilter {
    @Getter
    private Specification<Product> specification;
    @Getter
    private StringBuilder filter;

    public ProductFilter(Map<String, String> map) {
        this.specification = Specification.where(null);
        this.filter = new StringBuilder();

        if (map.containsKey("minPrice") && !map.get("minPrice").isEmpty()){
            BigDecimal price = new BigDecimal(map.get("minPrice").replace(',', '.')).setScale(2, BigDecimal.ROUND_DOWN);
            specification = specification.and(ProductSpecifications.priceGreaterThanOrEqual(price));
            filter.append("&minPrice=").append(price);
        }

        if (map.containsKey("maxPrice") && !map.get("maxPrice").isEmpty()){
            BigDecimal price = new BigDecimal(map.get("maxPrice").replace(',', '.')).setScale(2, BigDecimal.ROUND_DOWN);
            specification = specification.and(ProductSpecifications.priceLessThanOrEqual(price));
            filter.append("&maxPrice=").append(price);
        }

        if (map.containsKey("title") && !map.get("title").isEmpty()) {
            specification = specification.and(ProductSpecifications.findByTitle(map.get("title")));
            filter.append("&title=" + map.get("title"));
        }
    }
}
