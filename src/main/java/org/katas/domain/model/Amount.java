package org.katas.domain.model;

import java.math.BigDecimal;

import static org.katas.domain.model.Currency.EURO;

public record Amount(BigDecimal value, Currency currency) {

    public static final Amount ZERO_EURO = new Amount(new BigDecimal(0), EURO);

    public static Amount of(Long value, Currency currency) {
        return new Amount(new BigDecimal(value), currency);
    }

    public boolean isGreaterThan(Amount other) {
        if (other == null) return false;
        if (other.currency() != currency()) {
            throw new IllegalArgumentException("Cannot compare amount with currency " + currency() + " to " + other.currency());
        };
        return this.value.compareTo(other.value) > 0;
    }
    public Amount plus(Amount anotherAmount) {
        if (anotherAmount.currency!= currency()){
            throw new IllegalArgumentException("Can't add another amount with currency " + anotherAmount.currency);
        }
        return new Amount(value.add(anotherAmount.value), currency);
    }
}
