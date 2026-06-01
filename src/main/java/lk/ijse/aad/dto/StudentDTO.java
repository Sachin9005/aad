package lk.ijse.aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    Long id;
    String firstName;
    String lastName;
    String DOB;
    String address;
}
