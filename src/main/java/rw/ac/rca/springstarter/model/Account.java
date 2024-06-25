package rw.ac.rca.springstarter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

   @Column(name = "accountNumber" , nullable = false)

    private String accountNumber;

    @Column(name = "lastUpdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @PrePersist
    public void prePersist() {
        this.lastUpdate = new Date();
    }
    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = new Date();
    }
}
