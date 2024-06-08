package com.kam.ecommerce.product.service;

import com.kam.ecommerce.product.exception.ProductNotFoundException;
import com.kam.ecommerce.product.exception.ProductPurchaseException;
import com.kam.ecommerce.product.helper.ProductPurchaseRequest;
import com.kam.ecommerce.product.helper.ProductPurchaseResponse;
import com.kam.ecommerce.product.helper.ProductRequest;
import com.kam.ecommerce.product.helper.ProductResponse;
import com.kam.ecommerce.product.mapper.ProductMapper;
import com.kam.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;
    public Integer createProduct(ProductRequest request) {
        return repository.save(productMapper.toProduct(request)).getId();
    }

    public void updateProduct(ProductRequest request) {
        var product = repository.findById(request.id())
                .orElseThrow( ()-> new ProductNotFoundException(
                        String.format("Cannot update product:: No product found with the provided ID:: %s", request.id())
                ) );
        productMapper.mergeProduct(product, request);
        repository.save( product);
    }

    public List<ProductResponse> getAllProduct() {
        return repository.findAll()
                .stream()
                .map( productMapper::fromProduct )
                .collect(Collectors.toList());
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(  ProductPurchaseRequest::productId )
                .toList();

        var storedProducts =  repository.findAllByIdInOrderById(productIds);
        if( productIds.size() != storedProducts.size() ){
            throw new ProductPurchaseException("One or more products does not exists");
        }
        var storesRequest =  request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i< storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storesRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID :: "+ product.getId());
            }
            //Calculate rest quantity and update on the db
            var newAvailableQuantity = product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchasedProducts;
    }

    public boolean existById(int id) {
        return  repository.findById(id).isPresent();
    }

    public ProductResponse getProductById(int id) {
        return repository.findById(id)
                .map(productMapper::fromProduct)
                .orElseThrow( ()-> new ProductNotFoundException(
                        String.format("Cannot update product:: No product found with the provided ID:: %s", id)
                ));
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
