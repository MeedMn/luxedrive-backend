package ma.jee.luxedriveBackend.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfos {
    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}
