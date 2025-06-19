package org.katas.domain.service;

import org.katas.domain.model.Amount;
import org.katas.domain.model.Product;
import org.katas.domain.api.ComputeCartPriceUseCase;
import org.katas.domain.model.cart.Cart;
import org.katas.domain.model.customer.Customer;
import org.katas.domain.model.customer.CustomerId;
import org.katas.domain.spi.CartRepository;

import static org.katas.domain.model.Amount.ZERO_EURO;

public class CartPricerService implements ComputeCartPriceUseCase {
    private final CartRepository cartRepository;

    public CartPricerService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Amount computeCartPrice(Customer customer) {
        Cart cart = findCartByCustomerId(customer.getCustomerId());

        return cart.getItems().stream()
                .map(cartItem -> cartItem.product())
                .map(product -> getPrice(product,customer))
                .reduce(Amount::plus)
                .orElse(ZERO_EURO);
    }

    private Amount getPrice(Product product, Customer customer) {
        return customer.getProductPricingStrategy().getProductPrice(product);
    }

    private Cart findCartByCustomerId(CustomerId customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("cart not found for customer with id "+ customerId.value()));
    }
}
