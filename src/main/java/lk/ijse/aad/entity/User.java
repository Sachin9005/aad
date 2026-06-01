package lk.ijse.aad.entity;

import jakarta.persistence.*;
import lk.ijse.aad.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
//getter,Setter,toString,equals
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    //use to send and get enum data as String data,Search and Save this enum
    private UserStatus status;
}
