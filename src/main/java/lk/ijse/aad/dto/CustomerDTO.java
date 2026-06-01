package lk.ijse.aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Long cusId;
    private String cusName;
    private String cusAddress;
    List<SaveOrderDTO> orders;
}
