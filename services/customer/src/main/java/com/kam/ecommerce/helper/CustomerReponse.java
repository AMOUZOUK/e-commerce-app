package com.kam.ecommerce.helper;

import com.kam.ecommerce.customer.Address;
import jakarta.validation.constraints.NotNull;

public record CustomerReponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
