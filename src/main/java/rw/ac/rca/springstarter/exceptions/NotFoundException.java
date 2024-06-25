package rw.ac.rca.springstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import rw.ac.rca.springstarter.dto.response.ErrorResponse;
import rw.ac.rca.springstarter.dto.response.Response;
import rw.ac.rca.springstarter.enums.EResponseType;
import rw.ac.rca.springstarter.payload.ApiSecondResponse;


import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

    public ResponseEntity<ApiSecondResponse> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("FAILED TO GET RESOURCE", details);
        Response<ErrorResponse> response = new Response<>();
        response.setResponseType(EResponseType.RESOURCE_NOT_FOUND);
        response.setPayload(errorResponse);
        return  ResponseEntity.ok(ApiSecondResponse.fail(this.getMessage()));
    }
}