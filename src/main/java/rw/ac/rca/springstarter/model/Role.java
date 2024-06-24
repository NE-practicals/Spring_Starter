package rw.ac.rca.springstarter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import rw.ac.rca.springstarter.enums.Roles;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User>users;
    public Role(String roleName) {
        this.roleName = roleName;
    }


    public Role() {

    }
}
