package ma.jee.luxedriveBackend.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}
