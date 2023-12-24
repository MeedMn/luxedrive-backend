package ma.jee.luxedriveBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;
}
