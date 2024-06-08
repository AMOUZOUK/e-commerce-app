package com.kam.ecommerce.product.Controller;

import com.kam.ecommerce.product.helper.ProductPurchaseRequest;
import com.kam.ecommerce.product.helper.ProductPurchaseResponse;
import com.kam.ecommerce.product.helper.ProductRequest;
import com.kam.ecommerce.product.helper.ProductResponse;
import com.kam.ecommerce.product.model.Product;
import com.kam.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok( service.createProduct(request));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> requests ){
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }
    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductRequest request){
        service.updateProduct(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok( service.getAllProduct());
    }

    @GetMapping("/exits/{product-id}")
    public ResponseEntity<Boolean> exitById( @PathVariable("product-id") int id){
        return ResponseEntity.ok(service.existById(id));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById( @PathVariable("product-id") int id ) {
        return ResponseEntity.ok(service.getProductById(id));
    }
    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("product-id") int id){
        service.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }
}
