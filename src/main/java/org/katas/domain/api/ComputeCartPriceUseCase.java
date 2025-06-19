package org.katas.domain.api;

import org.katas.domain.model.Amount;
import org.katas.domain.model.customer.Customer;

public interface ComputeCartPriceUseCase {

    Amount computeCartPrice(Customer customer);
}
