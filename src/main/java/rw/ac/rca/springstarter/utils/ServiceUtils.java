package rw.ac.rca.springstarter.utils;


import rw.ac.rca.springstarter.enums.EVisibility;
import rw.ac.rca.springstarter.model.User;

public class ServiceUtils {
    // method to check if a user is valid or deleted
    public static boolean isUserDeleted(User user) {
        return user.getVisibility().equals(EVisibility.HIDDEN);
    }
}
