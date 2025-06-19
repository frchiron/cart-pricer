package org.katas.domain.model.cart;

import org.katas.domain.model.customer.CustomerId;
import org.katas.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final CartId cartId;

    private final CustomerId customerId;

    private final List<CartItem> items;

    private Cart(CartId cartId, CustomerId customerId) {
        items = new ArrayList<>();
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public static Cart initializeCart(CustomerId customerId) {
        return new Cart(CartId.generateCartId(), customerId);
    }

    public void addProduct(Product product) {
        CartItem cartItem = new CartItem(product);
        items.add(cartItem);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public CartId getCartId() {
        return cartId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

}
