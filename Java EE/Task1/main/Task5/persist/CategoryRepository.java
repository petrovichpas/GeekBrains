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
public class CategoryRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws Exception {
        if (entityManager.createQuery("SELECT COUNT(c) FROM Category c", Long.class).getSingleResult() == 0) {
            try {
                userTransaction.begin();
                saveOrUpdate(new Category(null, "Одежда", entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList()));
                saveOrUpdate(new Category(null, "Обувь", entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList()));
                saveOrUpdate(new Category(null, "Чистящие средства", entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList()));
                userTransaction.commit();
            } catch (Exception ex) {
                userTransaction.rollback();
            }
        }
    }

    public List<Category> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) entityManager.persist(category);
        entityManager.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createQuery("DELETE FROM Category c WHERE c.id = :id").setParameter("id", id).executeUpdate();
    }
}
