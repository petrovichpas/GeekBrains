package ru.geekbrains.listener;

import ru.geekbrains.persist.Product;
import ru.geekbrains.repository.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        productRepository.saveOrUpdate(new Product(null, "Product  1", "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2", "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3", "Description of product 3", new BigDecimal(300)));
        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
