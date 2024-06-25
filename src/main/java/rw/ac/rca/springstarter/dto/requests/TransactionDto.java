package rw.ac.rca.springstarter.dto.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Data
public class TransactionDto {

    @NotBlank
    private Long amount;

    @NotBlank
    private Long accountId;

}
