package com.kam.ecommerce.helper;

import com.kam.ecommerce.customer.Address;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstname,
        @NotNull(message = "Customer lastname is required")
        String lastname,
        @NotNull(message = "Customer email is required")
        String email,
        Address address
) {
}
