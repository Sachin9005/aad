package lk.ijse.aad.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SaveOrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private Long customerId;
}
