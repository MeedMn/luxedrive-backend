package ma.jee.luxedriveBackend.dto.response;

import lombok.*;
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
    private CustomerResponse customer;
    private CarResponse car;
}
