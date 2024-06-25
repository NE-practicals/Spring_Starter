package rw.ac.rca.springstarter.services;




import rw.ac.rca.springstarter.model.Person;
import rw.ac.rca.springstarter.model.User;

import java.util.UUID;

public interface IUserService {

    public  boolean isUserLoggedIn();
    public User getLoggedInUser();
    public User getUserById(long uuid);
    public boolean isNotUnique(Person user);
    public boolean validateUserEntry(Person user);
}
