package ru.geekbrains.persist.enity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer cost;

    @OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
    private List<Order> orderList;
//    @JoinTable(name = "orders_tbl", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "buyers_id"))

    public Product() {
    }

    public Product(Long id, String title, Integer cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public List<Order> getUserList() {
        return orderList;
    }

    public void setUserList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
