package rw.ac.rca.springstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.springstarter.model.Account;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByCustomerId(Long customerId);


}
