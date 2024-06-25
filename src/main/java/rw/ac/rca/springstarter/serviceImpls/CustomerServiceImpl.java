package rw.ac.rca.springstarter.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.springstarter.dto.requests.CreateCustomerDTO;
import rw.ac.rca.springstarter.enums.Roles;
import rw.ac.rca.springstarter.exceptions.BadRequestException;
import rw.ac.rca.springstarter.model.Customer;
import rw.ac.rca.springstarter.model.Role;
import rw.ac.rca.springstarter.model.User;
import rw.ac.rca.springstarter.repositories.ICustomerRepository;
import rw.ac.rca.springstarter.repositories.IRoleRepository;
import rw.ac.rca.springstarter.repositories.IUserRepository;
import rw.ac.rca.springstarter.services.ICustomerService;
import rw.ac.rca.springstarter.utils.ExceptionUtils;
import rw.ac.rca.springstarter.utils.Hash;
import rw.ac.rca.springstarter.utils.Utility;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final IUserRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final MailServiceImpl mailService;
    private final IRoleRepository roleRepository;


    @Override
    public Customer createCustomer(CreateCustomerDTO customerDTO) {
        try {
           if(userRepository.findUserByEmail(customerDTO.getEmail()).isPresent()){
                throw new BadRequestException("Email already in use");
              }
            User user1= new User(customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getEmail(),customerDTO.getDateOfBirth(),customerDTO.getGender(),customerDTO.getPhoneNumber(),customerDTO.getNationalId(),customerDTO.getUsername(), Utility.generatedCode());
            user1.setPassword(Hash.hashPassword(customerDTO.getPassword()));
            Customer customer= new Customer();
            customer.setUser(user1);

            Set<Role> roles= new HashSet<>();
            roles.add(roleRepository.findByRoleName(Roles.CUSTOMER.toString()));
            user1.setRoles(roles);
            userRepository.save(user1);
            customerRepository.save(customer);
            mailService.sendAccountVerificationEmail(user1);
            return customer;

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;

        }
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
