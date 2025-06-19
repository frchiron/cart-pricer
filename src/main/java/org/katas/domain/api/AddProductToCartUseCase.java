package org.katas.domain.api;

import org.katas.domain.model.customer.CustomerId;
import org.katas.domain.model.Product;

public interface AddProductToCartUseCase {

    void addProductToCustomerCart(Product product, CustomerId customerId);
}
