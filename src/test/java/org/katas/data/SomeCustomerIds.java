package org.katas.data;

import org.katas.domain.model.customer.CustomerId;

public class SomeCustomerIds {
    private SomeCustomerIds(){};

    public static final CustomerId ERIC_CUSTOMER_ID = CustomerId.of("125");
    public static final CustomerId FRANCK_CUSTOMER_ID = CustomerId.of("456");
    public static final CustomerId DANONE_CUSTOMER_ID = CustomerId.of("896");
    public static final CustomerId CHARLIES_MARKET_CUSTOMER_ID = CustomerId.of("896");
}
