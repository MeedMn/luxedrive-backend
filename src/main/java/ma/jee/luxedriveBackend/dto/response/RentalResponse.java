package ma.jee.luxedriveBackend.dto.response;

import lombok.*;
import ma.jee.luxedriveBackend.dto.request.CustomerRequest;
import ma.jee.luxedriveBackend.entity.enums.Status;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status;
    private CustomerRequest customer;
    private CarResponse car;
}
