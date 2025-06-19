package org.katas.domain.model.pricingstrategy;

import org.katas.domain.model.Amount;
import org.katas.domain.model.Product;

import static org.katas.domain.model.Currency.EURO;

public class PrivateCustomerProductPricingStrategy implements ProductPricingStrategy {
    @Override
    public Amount getProductPrice(Product product) {
            return switch (product) {
                case LAPTOP -> Amount.of(1200L, EURO);
                case PHONE_MID_RANGE ->Amount.of(800L, EURO) ;
                case PHONE_HIGH_END ->   Amount.of(1500L, EURO);
            };
    }
}
