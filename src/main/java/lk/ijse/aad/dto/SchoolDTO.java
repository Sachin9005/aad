package lk.ijse.aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDTO {
    private Long id;
    String name;
    String location;
}
