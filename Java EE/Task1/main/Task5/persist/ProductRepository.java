package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                userTransaction.begin();
                saveOrUpdate(new Product(null, "Product  1", "Description of product 1", new BigDecimal(100), entityManager.find(Category.class, 1)));
                saveOrUpdate(new Product(null, "Product  2", "Description of product 2", new BigDecimal(200), entityManager.find(Category.class, 2)));
                saveOrUpdate(new Product(null, "Product  3", "Description of product 3", new BigDecimal(200), entityManager.find(Category.class, 3)));
                userTransaction.commit();
            } catch (Exception ex) {
                userTransaction.rollback();
            }
        }
    }

    public List<Product> findAll() {
        return entityManager.createNamedQuery("findAll", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) entityManager.persist(product);
        entityManager.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteById").setParameter("id", id).executeUpdate();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAll", Long.class).getSingleResult();
    }
}
