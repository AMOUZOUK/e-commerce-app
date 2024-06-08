package com.kam.ecommerce.product.helper;

import com.kam.ecommerce.product.model.Product;

import java.util.List;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        List<Product> products
) {
}
