package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {
    private CartService cartService;
    private ProductRepr expectedProduct;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
        expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setName("TestProduct");
        expectedProduct.setPrice(new BigDecimal(555));
        expectedProduct.setCategoryName("TestCategory");
        expectedProduct.setBrandName("TestBrand");
    }

    @Test
    public void testAddProductQty() {
        cartService.addProductQty(expectedProduct, 1);
        List<LineItem> lineItems = cartService.getLineItems();
        LineItem lineItem = lineItems.get(0);

        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());
        assertEquals(expectedProduct.getId(), lineItem.getProductRepr().getId());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
        assertEquals(expectedProduct.getPrice(), lineItem.getProductRepr().getPrice());
        assertEquals(expectedProduct.getCategoryName(), lineItem.getProductRepr().getCategoryName());
        assertEquals(expectedProduct.getBrandName(), lineItem.getProductRepr().getBrandName());
    }

    @Test
    public void testRemoveProductQty() {
        cartService.addProductQty(expectedProduct, 4);
        cartService.removeProductQty(expectedProduct, 1);
        List<LineItem> lineItems = cartService.getLineItems();
        assertEquals(3, lineItems.get(0).getQty());
    }

    @Test
    public void testRemoveProduct() {
        cartService.addProductQty(expectedProduct, 3);
        cartService.removeProduct(cartService.getLineItems().get(0));
        assertEquals(0, cartService.getLineItems().size());
    }

    @Test
    public void testRemoveAllProduct() {
        cartService.addProductQty(expectedProduct, 8);
        cartService.removeAllProduct();
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testUpdateCart() {
        cartService.addProductQty(expectedProduct, 8);
        cartService.removeProductQty(expectedProduct, 4);
        cartService.updateCart(cartService.getLineItems().get(0));
        assertEquals(4, cartService.getLineItems().get(0).getQty());
        assertEquals(new BigDecimal(2220), cartService.getSubTotal());
    }
}
