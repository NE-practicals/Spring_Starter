package rw.ac.rca.springstarter.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import rw.ac.rca.springstarter.enums.EResponseType;

@Getter
@Setter
@RequiredArgsConstructor
public class Response<T> {
    private EResponseType responseType;
    private T payload;
}
