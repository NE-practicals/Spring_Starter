package rw.ac.rca.springstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import rw.ac.rca.springstarter.dto.response.ErrorResponse;
import rw.ac.rca.springstarter.dto.response.Response;
import rw.ac.rca.springstarter.enums.EResponseType;
import rw.ac.rca.springstarter.payload.ApiSecondResponse;


import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message){
        super(message);
    }

    public ResponseEntity<ApiSecondResponse> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
    ErrorResponse errorResponse = new ErrorResponse("Failed to get a resource", details);
        Response<ErrorResponse> response = new Response<>();
        response.setResponseType(EResponseType.EXCEPTION);
        response.setPayload(errorResponse);
        return  ResponseEntity.ok(ApiSecondResponse.fail(this.getMessage()));
    }

    public BadRequestException(String message , Throwable cause){
        super(message , cause);
    }
}