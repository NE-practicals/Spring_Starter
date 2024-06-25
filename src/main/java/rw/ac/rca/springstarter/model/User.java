package rw.ac.rca.springstarter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.ac.rca.springstarter.enums.EGender;
import rw.ac.rca.springstarter.enums.EUserStatus;
import rw.ac.rca.springstarter.enums.EVisibility;
import rw.ac.rca.springstarter.model.Person;
import rw.ac.rca.springstarter.model.Role;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
//@OnDelete(action = OnDeleteAction.CASCADE)
@NoArgsConstructor
@Data
public class User extends Person {

    @Column(name = "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must be strong")
    private String password;

    @Column(name = "username", nullable = false)
    private String username;
    @Enumerated(EnumType.STRING)
    private EUserStatus accountStatus = EUserStatus.PENDING;

    private  String activationCode;
    @Column(name = "visibilty", nullable = false)
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private EVisibility visibility = EVisibility.VISIBLE;


    @Column(name = "profile_picture", nullable = true)
    private String profilePicture = null;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();
    public User(String firstName, String lastName, String email, Date dob, EGender gender, String phoneNumber, String nationalId, String username, String activationCode) {
        super(firstName, lastName, email,dob,gender, phoneNumber, nationalId);
        this.username = username;
        this.activationCode = activationCode;
    }
}