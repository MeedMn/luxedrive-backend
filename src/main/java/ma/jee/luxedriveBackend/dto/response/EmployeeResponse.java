package ma.jee.luxedriveBackend.dto.response;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private String address;
    private String phoneNumber;
}
