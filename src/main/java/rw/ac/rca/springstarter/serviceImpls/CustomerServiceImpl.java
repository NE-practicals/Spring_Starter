package rw.ac.rca.springstarter.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.dto.requests.CreateCustomerDto;
import rw.ac.rca.springstarter.exceptions.BadRequestException;
import rw.ac.rca.springstarter.model.Customer;
import rw.ac.rca.springstarter.repositories.CustomerRepository;
import rw.ac.rca.springstarter.services.CustomerService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
       //try and catch block
        try{
            //check if user does not exist
            if(customerRepository.findByEmail(createCustomerDto.getEmail()).isPresent()){
                throw new BadRequestException("Email already exist");
            }

            //create a new user
            Customer customer= new Customer();
            customer.setFirstName(createCustomerDto.getFirstName());
            customer.setLastName(createCustomerDto.getLastName());
            customer.setEmail(createCustomerDto.getEmail());
            customer.setMobile(createCustomerDto.getMobile());
            customer.setDob(createCustomerDto.getDob());


            //save the user
            return customerRepository.save(customer);

        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Customer updateCustomer(CreateCustomerDto createCustomerDto) {
        return null;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try{
            return customerRepository.findAll();
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
