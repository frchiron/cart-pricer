package org.katas.domain.model.customer;

import org.katas.domain.model.pricingstrategy.ProductPricingStrategy;

public abstract class Customer {

    private CustomerId customerId;


    Customer(CustomerId customerId) {
        this.customerId = customerId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public abstract ProductPricingStrategy getProductPricingStrategy();
}
