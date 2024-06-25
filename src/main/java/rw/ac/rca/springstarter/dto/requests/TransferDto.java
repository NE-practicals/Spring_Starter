package rw.ac.rca.springstarter.dto.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TransferDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Long amount;
}
