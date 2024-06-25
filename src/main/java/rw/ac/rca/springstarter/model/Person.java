package rw.ac.rca.springstarter.model;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import lombok.NoArgsConstructor;
import rw.ac.rca.springstarter.audits.Initializer;
import rw.ac.rca.springstarter.enums.EGender;
import rw.ac.rca.springstarter.enums.EVisibility;


import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class Person extends Initializer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;


    @Column(name = "dob")
    private Date dob;

    private EGender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "national_id")
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private EVisibility visibility = EVisibility.VISIBLE;



    public Person(String firstName, String lastName, String email, Date dob, EGender gender, String phoneNumber, String nationalId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
    }
    //private User (After user creation)
}