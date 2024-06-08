package com.kam.ecommerce.mapper;

import com.kam.ecommerce.customer.Customer;
import com.kam.ecommerce.helper.CustomerReponse;
import com.kam.ecommerce.helper.CustomerRequest;
import org.apache.commons.lang.StringUtils;


public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if( request == null ){
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }
    public void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank( request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank( request.lastname())){
            customer.setFirstname(request.lastname());
        }
        if(StringUtils.isNotBlank( request.email())){
            customer.setFirstname(request.email());
        }
        if(request.address() != null ){
            customer.setAddress(request.address());
        }
    }
    public CustomerReponse fromConstomer(Customer customer) {
       return new CustomerReponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
