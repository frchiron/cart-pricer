package org.katas.domain.model.customer;

public record VatNumber(String value) {

    public static final VatNumber NOT_SET = new VatNumber(null);
}
