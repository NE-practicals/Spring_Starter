package rw.ac.rca.springstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.springstarter.dto.response.ErrorResponse;
import rw.ac.rca.springstarter.dto.response.Response;
import rw.ac.rca.springstarter.enums.EResponseType;


import java.util.ArrayList;
import java.util.List;

public class InternalServerErrorException  extends RuntimeException{
    public InternalServerErrorException(String message){
        super(message);
    }

    public ResponseEntity<?> getResponse(){
        List<String> details = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse(getMessage(),details);
        Response<ErrorResponse> response = new Response<>();
        response.setResponseType(EResponseType.LOGIN_FAILED);
        response.setPayload(errorResponse);
        return new ResponseEntity<Response>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}