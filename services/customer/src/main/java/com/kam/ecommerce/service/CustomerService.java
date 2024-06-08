package com.kam.ecommerce.service;

import com.kam.ecommerce.exception.CustomerNotFoundException;
import com.kam.ecommerce.helper.CustomerReponse;
import com.kam.ecommerce.helper.CustomerRequest;
import com.kam.ecommerce.mapper.CustomerMapper;
import com.kam.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository repository;
    private CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        var customer =  repository.save( mapper.toCustomer(request) );
        return customer.getId();
    }
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                        .orElseThrow( () -> new CustomerNotFoundException(
                                String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                        ) );
        mapper.mergerCustomer(customer, request);
        repository.save(customer);
    }

    public List<CustomerReponse> findAllCustomers() {

     return repository.findAll()
             .stream()
             .map( mapper::fromConstomer )
             .collect(Collectors.toList());
    }

    public CustomerReponse findByIdCustomer(String id) {
        return repository.findById(id)
                .map(mapper::fromConstomer)
                .orElseThrow( ()-> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID:: %s", id)
                ));
    }

    public boolean existById(String id) {
        return repository.findById(id).isPresent();
    }

    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }
}
