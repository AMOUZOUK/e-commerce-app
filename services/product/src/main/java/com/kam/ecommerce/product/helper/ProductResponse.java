package com.kam.ecommerce.product.helper;

import com.kam.ecommerce.product.model.Category;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
