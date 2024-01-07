package ma.jee.luxedriveBackend.service;

import ma.jee.luxedriveBackend.dto.request.RentalRequest;
import ma.jee.luxedriveBackend.dto.response.RentalResponse;
import ma.jee.luxedriveBackend.entity.Car;
import ma.jee.luxedriveBackend.entity.Customer;
import ma.jee.luxedriveBackend.entity.Rental;
import ma.jee.luxedriveBackend.entity.auth.User;
import ma.jee.luxedriveBackend.entity.enums.Status;
import ma.jee.luxedriveBackend.entity.enums.StatusCar;
import ma.jee.luxedriveBackend.exception.EntityNotFoundException;
import ma.jee.luxedriveBackend.mapper.RentalMapper;
import ma.jee.luxedriveBackend.repository.CarRepository;
import ma.jee.luxedriveBackend.repository.CustomerRepository;
import ma.jee.luxedriveBackend.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public List<RentalResponse> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream().map(RentalMapper::RentalToRentalResponse).toList();
    }

    public RentalResponse getRentalById(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental", rentalId));
        return RentalMapper.RentalToRentalResponse(rental);
    }

    public RentalResponse createRental(RentalRequest rentalRequest) {
        Rental rental = RentalMapper.RentalRequestToRental(rentalRequest);
        Customer customer = customerRepository.findById(rentalRequest.getCustomer())
                .orElseThrow(() -> new EntityNotFoundException("Customer", rentalRequest.getCustomer()));
        Car car = carRepository.findById(rentalRequest.getCar())
                .orElseThrow(() -> new EntityNotFoundException("Car", rentalRequest.getCar()));
        long daysBetween = ChronoUnit.DAYS.between(rental.getStartDate(),rental.getEndDate());
        rental.setCustomer(customer);
        rental.setCar(car);
        rental.setTotalCost(car.getPrice()*daysBetween);
        Rental savedRental = rentalRepository.save(rental);
        return RentalMapper.RentalToRentalResponse(savedRental);
    }

    public RentalResponse updateRental(Long rentalId, RentalRequest rentalRequest) {
        return rentalRepository.findById(rentalId)
                .map(r -> {
                    r.setStartDate(rentalRequest.getStartDate());
                    r.setEndDate(rentalRequest.getEndDate());
                    r.setStatus(Status.valueOf(rentalRequest.getStatus()));
                    rentalRepository.save(r);
                    return RentalMapper.RentalToRentalResponse(r);
                }).orElseThrow(() -> new EntityNotFoundException("Rental", rentalId));
    }
    public String ChangeStatus(Long rentalId,String status){
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental", rentalId));
        rental.setStatus(Status.valueOf(status));
        rentalRepository.save(rental);
        Car car = carRepository.findById(rental.getCar().getId())
                .orElseThrow(() -> new EntityNotFoundException("Car", rental.getCar().getId()));
        if(status.equals("ACTIVE")){
            car.setStatus(StatusCar.RENTED);
        }else{
            car.setStatus(StatusCar.AVAILABLE);
        }
        carRepository.save(car);
        return "Status Changed";

    }
    public String deleteRental(Long rentalId) {
        rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental", rentalId));
        rentalRepository.deleteById(rentalId);
        return "Rental Deleted";
    }
}

