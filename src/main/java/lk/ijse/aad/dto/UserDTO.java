package lk.ijse.aad.dto;

import lk.ijse.aad.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private UserStatus status;
}
