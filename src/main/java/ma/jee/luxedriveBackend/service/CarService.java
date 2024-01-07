package ma.jee.luxedriveBackend.service;

import ma.jee.luxedriveBackend.dto.request.CarRequest;
import ma.jee.luxedriveBackend.dto.response.CarResponse;
import ma.jee.luxedriveBackend.entity.Car;
import ma.jee.luxedriveBackend.entity.enums.StatusCar;
import ma.jee.luxedriveBackend.exception.EntityNotFoundException;
import ma.jee.luxedriveBackend.mapper.CarMapper;
import ma.jee.luxedriveBackend.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarMapper::CarToCarResponse).toList();
    }
    public CarResponse getCarById(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car", carId));
        return CarMapper.CarToCarResponse(car);
    }
    public CarResponse createCar(CarRequest carRequest) {
        Car car = CarMapper.CarRequestToCar(carRequest);
        car.setStatus(StatusCar.valueOf(carRequest.getStatus()));
        Car savedCar = carRepository.save(car);
        return CarMapper.CarToCarResponse(savedCar);
    }
    public CarResponse updateCar(Long carId, CarRequest carRequest) {
        return carRepository.findById(carId)
                .map(c->{
                    c.setTitle(carRequest.getTitle());
                    c.setDescription(carRequest.getDescription());
                    c.setImage(carRequest.getImage());
                    c.setMake(carRequest.getMake());
                    c.setFuel(carRequest.getFuel());
                    c.setPrice(carRequest.getPrice());
                    c.setModel(carRequest.getModel());
                    c.setYear(carRequest.getYear());
                    c.setRegistrationNumber(carRequest.getRegistrationNumber());
                    c.setStatus(StatusCar.valueOf(carRequest.getStatus()));
                    carRepository.save(c);
                    return CarMapper.CarToCarResponse(c);
                }).orElseThrow(() -> new EntityNotFoundException("Car", carId));
    }
    public String deleteCar(Long carId) {
        carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car", carId));
        carRepository.deleteById(carId);
        return "Car Deleted";
    }

}
