package rw.ac.rca.springstarter.services;

import rw.ac.rca.springstarter.dto.requests.CreateCustomerDto;
import rw.ac.rca.springstarter.model.Customer;

import java.util.List;

public interface CustomerService  {

    Customer createCustomer(CreateCustomerDto createCustomerDto);
    Customer updateCustomer(CreateCustomerDto createCustomerDto);
    Customer deleteCustomer(Long id);
    List<Customer> getAllCustomers();
}
