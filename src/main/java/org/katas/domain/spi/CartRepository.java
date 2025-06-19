package org.katas.domain.spi;

import org.katas.domain.model.cart.Cart;
import org.katas.domain.model.customer.CustomerId;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    List<Cart> findAll();

    Optional<Cart> findByCustomerId(CustomerId customerId);

    void save(Cart cart);
}
