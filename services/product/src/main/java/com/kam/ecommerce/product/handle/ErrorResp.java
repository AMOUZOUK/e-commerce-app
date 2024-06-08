package com.kam.ecommerce.product.handle;

import java.util.Map;

public record ErrorResp(
        Map<String, String> errors
) {
}
