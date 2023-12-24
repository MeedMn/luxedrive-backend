package ma.jee.luxedriveBackend.dto.response;

import jakarta.persistence.OneToMany;
import lombok.*;
import ma.jee.luxedriveBackend.entity.Rental;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}
