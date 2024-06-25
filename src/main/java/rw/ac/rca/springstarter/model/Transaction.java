package rw.ac.rca.springstarter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.audits.Initializer;
import rw.ac.rca.springstarter.enums.ETransactionType;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Transaction extends Initializer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;
    @Enumerated(EnumType.STRING)
    private ETransactionType transactionType;

    @ManyToOne
    private Account account;





}
