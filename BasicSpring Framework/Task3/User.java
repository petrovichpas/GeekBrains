package ru.geekbrains.persist.enity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String password;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Order> orderList;
    //    @JoinTable(name = "orders_tbl", joinColumns = @JoinColumn(name = "buyers_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))

    public User() {
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getProductList() {
        return orderList;
    }

    public void setProductList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
