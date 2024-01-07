package ma.jee.luxedriveBackend.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.jee.luxedriveBackend.entity.enums.StatusCar;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;
    private String make;
    private String fuel;
    private String model;
    private int year;
    private double price=0;
    private String registrationNumber;
    @Enumerated(EnumType.STRING)
    private StatusCar status;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
}
