package com.kam.ecommerce.product.mapper;

import com.kam.ecommerce.product.helper.CategoryRequeste;
import com.kam.ecommerce.product.helper.CategoryResponse;
import com.kam.ecommerce.product.model.Category;
import org.apache.commons.lang.StringUtils;

public class CategoryMapper {
    public Category toCategory(CategoryRequeste categoryRequeste) {
        if(categoryRequeste == null){
            return null;
        }
        return Category.builder()
                .id(categoryRequeste.id())
                .name(categoryRequeste.name())
                .description(categoryRequeste.description())
                .products(categoryRequeste.products())
                .build();
    }

    public void mergeCategory(Category category, CategoryRequeste categoryRequeste) {
        if(categoryRequeste.id() != null ){
           category.setId(categoryRequeste.id());
          }
        if(StringUtils.isNotBlank(categoryRequeste.name()) ){
           category.setName(categoryRequeste.name());
          }
        if(StringUtils.isNotBlank(categoryRequeste.description()) ){
            category.setDescription(categoryRequeste.description());
        }
        if(categoryRequeste.products() != null ){
            category.setProducts(categoryRequeste.products());
        }
    }

    public CategoryResponse fromCategory(Category category) {
       return new CategoryResponse(
               category.getId(),
               category.getName(),
               category.getDescription(),
               category.getProducts()
       );
    }
}
