package org.katas.domain.model.pricingstrategy;

import org.katas.domain.model.Amount;
import org.katas.domain.model.Product;

public interface ProductPricingStrategy {

    Amount getProductPrice(Product product);
}
