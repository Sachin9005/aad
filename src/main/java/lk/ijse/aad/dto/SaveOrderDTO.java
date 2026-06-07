package lk.ijse.aad.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SaveOrderDTO {
    private Long orderId;
    private String description;
    private double total;
    private Long customerId;

}
