package rw.ac.rca.springstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.springstarter.model.Message;

public interface IMessageRepository extends JpaRepository<Message,Long> {
}
