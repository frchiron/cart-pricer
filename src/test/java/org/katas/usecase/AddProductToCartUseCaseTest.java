package org.katas.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.katas.domain.model.cart.Cart;
import org.katas.domain.service.CartService;
import org.katas.domain.api.AddProductToCartUseCase;
import org.katas.domain.spi.CartRepository;
import org.katas.infra.repository.InMemoryCartRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.katas.data.SomeCustomerIds.*;
import static org.katas.domain.model.Product.LAPTOP;
import static org.katas.domain.model.Product.PHONE_MID_RANGE;

public class AddProductToCartUseCaseTest {

    private CartRepository cartRepository;
    private AddProductToCartUseCase addProductToCartUseCase;

    @BeforeEach
    void setUp() {
        cartRepository = new InMemoryCartRepository();
        addProductToCartUseCase = new CartService(cartRepository);
    }

    @Test
    void initialize_cart_with_laptop_item() {

        // When
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);

        // Then
        List<Cart> carts = cartRepository.findAll();
        assertThat(carts).hasSize(1);
        assertThat(carts.get(0).getItems()).hasSize(1);
        assertThat(carts.get(0).getItems().get(0).product()).isEqualTo(LAPTOP);
    }

    @Test
    void initialize_cart_with_laptop_and_phone_mid_range_item() {

        // When
        addProductToCartUseCase.addProductToCustomerCart(PHONE_MID_RANGE, ERIC_CUSTOMER_ID);
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);

        // Then
        List<Cart> carts = cartRepository.findAll();
        assertThat(carts).hasSize(1);
        assertThat(carts.get(0).getItems())
                .hasSize(2)
                .anyMatch(cartItem -> cartItem.product() == LAPTOP)
                .anyMatch(cartItem -> cartItem.product() == PHONE_MID_RANGE);
    }

    @Test
    void initialize_cart_with_2_laptops() {

        // When
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);

        // Then
        List<Cart> carts = cartRepository.findAll();
        assertThat(carts).hasSize(1);
        assertThat(carts.get(0).getItems())
                .hasSize(2)
                .allMatch(cartItem -> cartItem.product() == LAPTOP);

    }

    @Test
    void initialize_carts_for_2_distinct_customers() {

        // When
        addProductToCartUseCase.addProductToCustomerCart(PHONE_MID_RANGE, ERIC_CUSTOMER_ID);
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, FRANCK_CUSTOMER_ID);

        // Then
        List<Cart> carts = cartRepository.findAll();
        assertThat(carts).hasSize(2);
        assertThat(carts).filteredOn((cart) -> cart.getCustomerId().equals(ERIC_CUSTOMER_ID))
                .allMatch((cart) -> cart.getItems().size() ==1)
                .allMatch((cart) -> cart.getItems().get(0).product() == PHONE_MID_RANGE);

        assertThat(carts).filteredOn((cart) -> cart.getCustomerId().equals(FRANCK_CUSTOMER_ID))
                .allMatch((cart) -> cart.getItems().size() ==1)
                .allMatch((cart) -> cart.getItems().get(0).product() == LAPTOP);
    }
}
