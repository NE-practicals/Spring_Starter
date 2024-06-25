package rw.ac.rca.springstarter.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  ApResponse {
    private Boolean success;
    private String message;
    private Object data;

    private List<?> dataArray;

    public ApResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public ApResponse(boolean success, List<?> data, String message) {
        this.success = success;
        this.dataArray = data;
    }
    public ApResponse(boolean success, String message, Object object ){
        this.success = success;
        this.message = message;
        this.data = object;
    }



    public static ApResponse success(Object data) {
        return new ApResponse(true, data);
    }
    public static ApResponse success(List<?> data, String message) {
        return new ApResponse(true, data, message);
    }

    public static ApResponse success(String message, Object object) {
        return new ApResponse(true, message, object);
    }

    public static ApResponse fail(Object data) {
        return new ApResponse(false, data);
    }

    public static ApResponse success(String message) {
        return new ApResponse(true, message);
    }

    public static ApResponse fail(String message) {
        return new ApResponse(false, message);
    }
}