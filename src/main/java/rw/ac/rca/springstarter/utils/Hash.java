package rw.ac.rca.springstarter.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hash {
    private static final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public static String hashPassword(String password){
       return passwordEncoder.encode(password);
    }
    public static boolean isTheSame (String rawPassword,String hashedPassword){
        return passwordEncoder.matches(rawPassword,hashedPassword);
    }

}
