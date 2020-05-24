package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.enity.Order;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.enity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new User(null, "Vovka", "Gre213"));
        entityManager.persist(new User(null, "Petruha", "wersd231"));
        entityManager.persist(new User(null, "Valy", "544g4sd"));
        entityManager.persist(new Product(null, "Shapka", 13));
        entityManager.persist(new Product(null, "Tapki", 7));

        User user = entityManager.find(User.class, 3L);
        Product product = entityManager.find(Product.class, 2L);
        entityManager.persist(new Order(null, user, product));
        entityManager.getTransaction().commit();
    }
}
