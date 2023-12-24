package ma.jee.luxedriveBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private Long id;

    private String make;
    private String model;
    private int year;
    private String registrationNumber;
    private String status;
}
