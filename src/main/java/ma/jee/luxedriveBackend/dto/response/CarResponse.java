package ma.jee.luxedriveBackend.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ma.jee.luxedriveBackend.entity.enums.StatusCar;

public class CarResponse {
    private Long id;

    private String make;
    private String model;
    private int year;
    private String registrationNumber;
    private String status;
}
