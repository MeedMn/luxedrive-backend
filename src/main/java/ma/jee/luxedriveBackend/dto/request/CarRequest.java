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
    private String title;
    private String description;
    private String image;
    private String make;
    private String fuel;
    private String model;
    private double price;
    private int year;
    private String registrationNumber;
    private String status;
}
