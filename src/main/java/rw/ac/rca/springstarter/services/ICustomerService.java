package rw.ac.rca.springstarter.services;


import rw.ac.rca.springstarter.dto.requests.CreateCustomerDTO;
import rw.ac.rca.springstarter.model.Customer;

import java.util.List;

public interface ICustomerService {
    public Customer createCustomer(CreateCustomerDTO customerDTO);
    public List<Customer> getAllCustomers();

}
