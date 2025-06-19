package org.katas.domain.service;

import org.katas.domain.model.Product;
import org.katas.domain.api.AddProductToCartUseCase;
import org.katas.domain.model.cart.Cart;
import org.katas.domain.model.customer.CustomerId;
import org.katas.domain.spi.CartRepository;

import java.util.Optional;

public class CartService implements AddProductToCartUseCase {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addProductToCustomerCart(Product product, CustomerId customerId) {
        Optional<Cart> maybeCartCustomer = cartRepository.findByCustomerId(customerId);
        Cart cart = maybeCartCustomer.isPresent() ? maybeCartCustomer.get() : Cart.initializeCart(customerId);
        cart.addProduct(product);
        cartRepository.save(cart);
    }

}
