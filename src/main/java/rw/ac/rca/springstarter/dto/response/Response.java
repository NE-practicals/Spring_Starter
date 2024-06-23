package rw.ac.rca.springstarter.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.enums.ResponseType;

@Getter
@Setter
@RequiredArgsConstructor
public class Response<T> {
    private ResponseType responseType;
    private T payload;
}
