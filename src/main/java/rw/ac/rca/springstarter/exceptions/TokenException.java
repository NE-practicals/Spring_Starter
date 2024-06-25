package rw.ac.rca.springstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.springstarter.dto.response.ErrorResponse;
import rw.ac.rca.springstarter.dto.response.Response;
import rw.ac.rca.springstarter.enums.EResponseType;


import java.util.ArrayList;
import java.util.List;

public class TokenException extends Exception{
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public TokenException(String message){
        super(message);
    }

    public ResponseEntity<Response> getResponseEntity() {
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("You are not authorized to access this resource", details);
        Response<ErrorResponse> response = new Response<>();
        response.setPayload(errorResponse);
        response.setResponseType(EResponseType.UNAUTHORIZED);
        return new ResponseEntity<Response>(response , httpStatus);
    }
}
