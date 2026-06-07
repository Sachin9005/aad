package lk.ijse.aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Long cusId;
    private String cusName;
    private String cusAddress;
}
