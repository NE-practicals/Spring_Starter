package rw.ac.rca.springstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.springstarter.dto.response.ErrorResponse;
import rw.ac.rca.springstarter.dto.response.Response;
import rw.ac.rca.springstarter.enums.EResponseType;


import java.util.ArrayList;
import java.util.List;

public class LoginFailedException extends Exception{
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public LoginFailedException(String message){
        super(message);
    }

    ResponseEntity<Response> getResponseEntity(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
    ErrorResponse errorResponse = new ErrorResponse("Failed to Login",details);
        Response<ErrorResponse> response = new Response<>();
        response.setResponseType(EResponseType.LOGIN_FAILED);
        response.setPayload(errorResponse);
        return new ResponseEntity<Response>(response , HttpStatus.UNAUTHORIZED);
    }
}
