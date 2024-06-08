package com.kam.ecommerce.product.Controller;

import com.kam.ecommerce.product.helper.CategoryRequeste;
import com.kam.ecommerce.product.helper.CategoryResponse;
import com.kam.ecommerce.product.helper.ProductPurchaseResponse;
import com.kam.ecommerce.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryContorller {

    private CategoryService service;
    @PostMapping
    public ResponseEntity<Integer> createCategory(@RequestBody @Valid CategoryRequeste categoryRequeste){
        return ResponseEntity.ok(service.createCategory(categoryRequeste));
    }

    @PutMapping
    public ResponseEntity<Void> updateCategory(@RequestBody @Valid CategoryRequeste categoryRequeste){
        service.updateCategory(categoryRequeste);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }
    @GetMapping("/exist/{category-id}")
    public ResponseEntity<Boolean> existId( @PathVariable("category-id") @Valid int id){
        return ResponseEntity.ok(service.existId( id ));
    }
    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable("category-id") @Valid int id) {
        return ResponseEntity.ok(service.findCategoryById(id));
    }
    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("category-id") @Valid int id){
        service.deleteCategory(id);
        return ResponseEntity.accepted().build();
    }
}
