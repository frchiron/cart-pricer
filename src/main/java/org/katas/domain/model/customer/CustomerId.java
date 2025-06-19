package org.katas.domain.model.customer;

public record CustomerId(String value) {

    public static CustomerId of(String value) {
        return new CustomerId(value);
    }
}
