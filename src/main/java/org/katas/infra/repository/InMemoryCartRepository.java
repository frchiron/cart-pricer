package org.katas.infra.repository;


import org.katas.domain.model.cart.Cart;
import org.katas.domain.model.cart.CartId;
import org.katas.domain.model.customer.CustomerId;
import org.katas.domain.spi.CartRepository;

import java.util.*;

public class InMemoryCartRepository implements CartRepository {

    private final Map<CartId,Cart> carts = new HashMap<>();

    @Override
    public List<Cart> findAll() {
        return carts.values().stream().toList();
    }

    @Override
    public Optional<Cart> findByCustomerId(CustomerId customerId) {
        return carts.values().stream()
                .filter(cart -> cart.getCustomerId().equals(customerId))
                .findFirst();
    }

    @Override
    public void save(Cart cart) {
        carts.put(cart.getCartId(), cart);
    }
}
