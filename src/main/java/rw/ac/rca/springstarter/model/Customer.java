package rw.ac.rca.springstarter.model;

import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email" , nullable = false)
    private String email;


    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "lastUpdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Account> accounts;
    public Customer(String email, String lastName, String firstName, String mobile, Date dob) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
        this.dob = dob;
    }
    @PrePersist
    public void prePersist() {
        this.lastUpdate = new Date();
    }
    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = new Date();
    }

}
