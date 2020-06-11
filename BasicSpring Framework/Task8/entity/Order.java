package ru.geekbrains.springbootlesson.entity;//package ru.geekbrains.persist.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "orders_tbl")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "buyer_id")
//    private User buyer;
//
//    @ManyToOne
//    @JoinColumn(name = "good_id")
//    private Product good;
//
//    public Order() {
//    }
//
//    public Order(Long id, User buyer, Product good) {
//        this.id = id;
//        this.buyer = buyer;
//        this.good = good;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getBuyer() {
//        return buyer;
//    }
//
//    public void setBuyer(User buyer) {
//        this.buyer = buyer;
//    }
//
//    public Product getGood() {
//        return good;
//    }
//
//    public void setGood(Product good) {
//        this.good = good;
//    }
//}
