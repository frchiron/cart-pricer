package org.katas.domain.model.customer;

import org.katas.domain.model.Amount;
import org.katas.domain.model.Currency;
import org.katas.domain.model.pricingstrategy.DefaultProfessionalCustomerProductPricingStrategy;
import org.katas.domain.model.pricingstrategy.ProductPricingStrategy;
import org.katas.domain.model.pricingstrategy.ProfessionalCustomerWithHighRevenuePricingStrategy;

public class ProfessionalCustomer extends Customer {
    public static final Amount HIGH_REVENUE_MINIMUM_LIMIT = Amount.of(10000000L, Currency.EURO);
    private final Amount annualRevenue;

    public static ProfessionalCustomer of(CustomerId customerId, Amount annualRevenue) {
        return new ProfessionalCustomer(customerId, annualRevenue);
    }

    private ProfessionalCustomer(CustomerId customerId, Amount annualRevenue) {
        super(customerId);
        this.annualRevenue = annualRevenue;
    }

    @Override
    public ProductPricingStrategy getProductPricingStrategy() {
        if (annualRevenue.isGreaterThan(HIGH_REVENUE_MINIMUM_LIMIT)) {
            return new ProfessionalCustomerWithHighRevenuePricingStrategy();
        }
        return new DefaultProfessionalCustomerProductPricingStrategy();
    }
}
