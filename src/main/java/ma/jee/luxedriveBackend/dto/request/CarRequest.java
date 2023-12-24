package ma.jee.luxedriveBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String make;
    private String model;
    private int year;
    private String registrationNumber;
    private String status;
}
