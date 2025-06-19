package org.katas.domain.model.cart;

import java.util.UUID;

public record CartId(String value) {

    static CartId generateCartId() {
        String cartId = UUID.randomUUID().toString();
        return new CartId(cartId);
    }
}
