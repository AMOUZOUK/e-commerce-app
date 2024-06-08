package com.kam.ecommerce.product.mapper;

import com.kam.ecommerce.product.helper.ProductPurchaseResponse;
import com.kam.ecommerce.product.helper.ProductRequest;
import com.kam.ecommerce.product.helper.ProductResponse;
import com.kam.ecommerce.product.model.Category;
import com.kam.ecommerce.product.model.Product;
import org.apache.commons.lang.StringUtils;

public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        if(request == null ){
            return null;
        }
        var product = Product.builder()
                .id(request.id())
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder().id(request.id()).build())
                .build();
        return product;
    }

    public void mergeProduct(Product product, ProductRequest request) {
        if(StringUtils.isNotBlank(request.name())){
            product.setName(request.name());
        }
        if(StringUtils.isNotBlank(request.description())){
            product.setDescription(request.description());
        }
        if( request.availableQuantity() != 0){
            product.setAvailableQuantity(request.availableQuantity());
        }
        if(request.price() != null){
            product.setPrice(request.price());
        }
       /* if( request.categoryId() != null ){
            product.setCategory(request.categoryId());
        }*/
    }

    public ProductResponse fromProduct(Product product) {
        if(product == null){
            return null;
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
       return new ProductPurchaseResponse(
               product.getId(),
               product.getName(),
               product.getDescription(),
               product.getPrice(),
               product.getAvailableQuantity()
       );

    }
}
