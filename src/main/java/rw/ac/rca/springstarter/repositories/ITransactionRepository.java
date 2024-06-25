package rw.ac.rca.springstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.springstarter.model.Transaction;

public interface ITransactionRepository  extends JpaRepository<Transaction, Long> {

}
