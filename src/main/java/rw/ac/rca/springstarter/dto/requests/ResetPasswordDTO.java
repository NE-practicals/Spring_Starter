package rw.ac.rca.springstarter.dto.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResetPasswordDTO {
    String email;
    String newPassword;
}
