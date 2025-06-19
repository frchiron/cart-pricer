package org.katas.domain.model.pricingstrategy;

import org.katas.domain.model.Amount;
import org.katas.domain.model.Product;

import static org.katas.domain.model.Currency.EURO;

public class DefaultProfessionalCustomerProductPricingStrategy implements ProductPricingStrategy {
    @Override
    public Amount getProductPrice(Product product) {
            return switch (product) {
                case LAPTOP -> Amount.of(1000L, EURO);
                case PHONE_MID_RANGE ->Amount.of(600L, EURO) ;
                case PHONE_HIGH_END ->  Amount.of(1150L, EURO);
            };
    }
}
