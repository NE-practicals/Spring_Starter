package rw.ac.rca.springstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    HttpStatus statusCode=HttpStatus.NOT_FOUND;
    public NotFoundException(){}
    public NotFoundException(String message){
        super(message);
    }

}


