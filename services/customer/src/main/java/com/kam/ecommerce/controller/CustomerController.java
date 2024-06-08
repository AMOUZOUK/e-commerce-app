package com.kam.ecommerce.controller;

import com.kam.ecommerce.customer.Customer;
import com.kam.ecommerce.helper.CustomerReponse;
import com.kam.ecommerce.helper.CustomerRequest;
import com.kam.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(service.createCustomer( request ));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer( @RequestBody @Valid CustomerRequest request){
         service.updateCustomer(request);
         return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerReponse>> findAll(){
        return ResponseEntity.ok( service.findAllCustomers());
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> existById( @PathVariable("customer-id") String id){
        return ResponseEntity.ok(service.existById(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerReponse> findById( @PathVariable("customer-id") String id){
        return ResponseEntity.ok(service.findByIdCustomer(id));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(@PathVariable("customer-id") String id){
        service.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }

}
