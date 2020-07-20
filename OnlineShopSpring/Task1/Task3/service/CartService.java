package ru.geekbrains.service;

import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {

    void addProductQty(ProductRepr productRepr, int qty);

    void removeProductQty(ProductRepr productRepr, int qty);

    void removeProduct(LineItem lineItem);

    void updateCart(LineItem lineItem);

    List<LineItem> getLineItems();
    BigDecimal getSubTotal();
}
