package rw.ac.rca.springstarter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.audits.Initializer;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account", uniqueConstraints = {@UniqueConstraint(columnNames = {"accountNumber"})})

public class Account extends Initializer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @ManyToOne
    private Customer customer;

   @Column(name = "accountNumber" , nullable = false)

    private String accountNumber;


}
