package rw.ac.rca.springstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.springstarter.model.Customer;




public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
