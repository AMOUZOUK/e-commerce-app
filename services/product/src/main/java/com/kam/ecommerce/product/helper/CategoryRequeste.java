package com.kam.ecommerce.product.helper;
import com.kam.ecommerce.product.model.Product;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CategoryRequeste(
        Integer id,
        @NotNull(message = "Category name is required")
        String name,
        @NotNull(message = "description name is required")
        String description,
        List<Product> products
) {
}
