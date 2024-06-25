package rw.ac.rca.springstarter.dto.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class WithdrawDto {
    private Long accountId;
    private Long amount;
}
