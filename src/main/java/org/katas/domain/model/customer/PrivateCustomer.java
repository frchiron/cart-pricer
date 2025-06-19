package org.katas.domain.model.customer;

import org.katas.domain.model.pricingstrategy.PrivateCustomerProductPricingStrategy;
import org.katas.domain.model.pricingstrategy.ProductPricingStrategy;

public class PrivateCustomer extends Customer {

    public static PrivateCustomer of(CustomerId customerId) {
        return new PrivateCustomer(customerId);
    }
    private PrivateCustomer(CustomerId customerId) {
        super(customerId);
    }

    @Override
    public ProductPricingStrategy getProductPricingStrategy() {
        return new PrivateCustomerProductPricingStrategy();
    }
}
