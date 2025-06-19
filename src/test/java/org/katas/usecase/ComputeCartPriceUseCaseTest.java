package org.katas.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.katas.data.SomeCustomerIds;
import org.katas.domain.api.AddProductToCartUseCase;
import org.katas.domain.api.ComputeCartPriceUseCase;
import org.katas.domain.model.customer.Customer;
import org.katas.domain.model.customer.PrivateCustomer;
import org.katas.domain.model.customer.ProfessionalCustomer;
import org.katas.domain.model.Amount;
import org.katas.domain.service.CartPricerService;
import org.katas.domain.service.CartService;
import org.katas.domain.spi.CartRepository;
import org.katas.infra.repository.InMemoryCartRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.katas.data.SomeCustomerIds.*;
import static org.katas.domain.model.Currency.EURO;
import static org.katas.domain.model.Product.*;


public class ComputeCartPriceUseCaseTest {

    private CartRepository cartRepository;
    private AddProductToCartUseCase addProductToCartUseCase;
    private ComputeCartPriceUseCase computeCartPriceUseCase;

    @BeforeEach
    void setUp() {
        cartRepository = new InMemoryCartRepository();
        addProductToCartUseCase = new CartService(cartRepository);
        computeCartPriceUseCase = new CartPricerService(cartRepository);
    }

    @Test
    void compute_cart_price_for_a_private_customer_when_laptop_added() {
        // Given
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(PrivateCustomer.of(ERIC_CUSTOMER_ID));

        // Then
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(1200));
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
    }

    @Test
    void compute_cart_price_for_a_private_customer_when_2_laptops_added() {
        // Given
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(PrivateCustomer.of(ERIC_CUSTOMER_ID));

        // Then
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(2400));
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
    }


    @Test
    void compute_cart_price_for_a_private_customer_when_laptop_and_both_phones_added() {
        // Given
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);
        addProductToCartUseCase.addProductToCustomerCart(PHONE_MID_RANGE, ERIC_CUSTOMER_ID);
        addProductToCartUseCase.addProductToCustomerCart(PHONE_HIGH_END, ERIC_CUSTOMER_ID);

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(PrivateCustomer.of(ERIC_CUSTOMER_ID));

        // Then
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(3500));
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
    }

    @Test
    void returns_error_when_customer_has_no_active_cart(){
        // Given
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, ERIC_CUSTOMER_ID);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> computeCartPriceUseCase.computeCartPrice(PrivateCustomer.of(FRANCK_CUSTOMER_ID)));
    }

    @Test
    void compute_cart_price_for_a_high_revenue_professional_customer_when_laptop_added() {
        // Given
        Customer danoneProfessionalCustomer = ProfessionalCustomer.of(DANONE_CUSTOMER_ID, Amount.of(11000000L,EURO));
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, danoneProfessionalCustomer.getCustomerId());

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(danoneProfessionalCustomer);

        // Then
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(900));
    }

    @Test
    void compute_cart_price_for_a_high_revenue_professional_customer_when_laptop_and_both_phones_added() {
        // Given
        Customer danoneProfessionalCustomer = ProfessionalCustomer.of(DANONE_CUSTOMER_ID, Amount.of(11000000L,EURO));
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, danoneProfessionalCustomer.getCustomerId());
        addProductToCartUseCase.addProductToCustomerCart(PHONE_MID_RANGE, danoneProfessionalCustomer.getCustomerId());
        addProductToCartUseCase.addProductToCustomerCart(PHONE_HIGH_END, danoneProfessionalCustomer.getCustomerId());

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(danoneProfessionalCustomer);

        // Then
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(2450));
    }

    @Test
    void compute_cart_price_for_a_professional_customer_with_low_revenue_when_laptop_added() {
        // Given
        Customer charliesMarketProfessionalCustomer = ProfessionalCustomer.of(CHARLIES_MARKET_CUSTOMER_ID, Amount.of(9000000L,EURO));
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, charliesMarketProfessionalCustomer.getCustomerId());

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(charliesMarketProfessionalCustomer);

        // Then
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(1000));
    }

    @Test
    void compute_cart_price_for_a_professional_customer_with_low_revenue_when_laptop_and_both_phones_added() {
        // Given
        Customer charliesMarketProfessionalCustomer = ProfessionalCustomer.of(CHARLIES_MARKET_CUSTOMER_ID, Amount.of(9000000L,EURO));
        addProductToCartUseCase.addProductToCustomerCart(LAPTOP, charliesMarketProfessionalCustomer.getCustomerId());
        addProductToCartUseCase.addProductToCustomerCart(PHONE_MID_RANGE, charliesMarketProfessionalCustomer.getCustomerId());
        addProductToCartUseCase.addProductToCustomerCart(PHONE_HIGH_END, charliesMarketProfessionalCustomer.getCustomerId());

        // When
        Amount cartTotalAmount = computeCartPriceUseCase.computeCartPrice(charliesMarketProfessionalCustomer);

        // Then
        assertThat(cartTotalAmount.currency()).isEqualTo(EURO);
        assertThat(cartTotalAmount.value()).isEqualTo(new BigDecimal(2750));
    }
}
