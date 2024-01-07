package ma.jee.luxedriveBackend.service;

import ma.jee.luxedriveBackend.dto.request.CustomerRequest;
import ma.jee.luxedriveBackend.dto.request.RentalRequest;
import ma.jee.luxedriveBackend.dto.response.CustomerResponse;
import ma.jee.luxedriveBackend.entity.Car;
import ma.jee.luxedriveBackend.entity.Customer;
import ma.jee.luxedriveBackend.entity.Rental;
import ma.jee.luxedriveBackend.exception.EntityNotFoundException;
import ma.jee.luxedriveBackend.mapper.CustomerMapper;
import ma.jee.luxedriveBackend.mapper.RentalMapper;
import ma.jee.luxedriveBackend.repository.CarRepository;
import ma.jee.luxedriveBackend.repository.CustomerRepository;
import ma.jee.luxedriveBackend.repository.RentalRepository;
import ma.jee.luxedriveBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CustomerService{
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    public CustomerService(CustomerRepository customerRepository,RentalRepository rentalRepository,CarRepository carRepository,UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.rentalRepository=rentalRepository;
        this.carRepository=carRepository;
        this.userRepository = userRepository;
    }
    public List<CustomerResponse> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerMapper::CustomerToCustomerResponse).toList();
    }
    public CustomerResponse getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer", customerId));
        return CustomerMapper.CustomerToCustomerResponse(customer);
    }
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerMapper.CustomerRequestToCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.CustomerToCustomerResponse(savedCustomer);
    }
    public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
        return customerRepository.findById(customerId)
                .map(c->{
                    c.setEmail(customerRequest.getEmail());
                    c.setAddress(customerRequest.getAddress());
                    c.setFullName(customerRequest.getFullName());
                    c.setPhoneNumber(customerRequest.getPhoneNumber());
                    customerRepository.save(c);
                    return CustomerMapper.CustomerToCustomerResponse(c);
                }).orElseThrow(() -> new EntityNotFoundException("Customer", customerId));
    }
    public String deleteCustomer(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer", customerId));
        customerRepository.deleteById(customerId);
        return "Customer Deleted";
    }
        public String assignRentalToCustomer(String customerEmail, RentalRequest rentalRequest) {
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new EntityNotFoundException("Customer", customerEmail));
        Car car = carRepository.findById(rentalRequest.getCar())
                .orElseThrow(() -> new EntityNotFoundException("Car", rentalRequest.getCar()));
        Rental rental = RentalMapper.RentalRequestToRental(rentalRequest);
        long daysBetween = ChronoUnit.DAYS.between(rental.getStartDate(),rental.getEndDate());
        rental.setCustomer(customer);
        rental.setCar(car);
        rental.setTotalCost(car.getPrice()*daysBetween);
        customer.getRentals().add(rental);
        customerRepository.save(customer);
        rentalRepository.save(rental);
        return "Rental assigned";
    }
}
