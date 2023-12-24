package ma.jee.luxedriveBackend.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.jee.luxedriveBackend.entity.Customer;
import ma.jee.luxedriveBackend.entity.enums.Status;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status;
    private Long customer;
    private Long car;
}
