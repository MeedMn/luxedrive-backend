package ma.jee.luxedriveBackend.mapper;

import ma.jee.luxedriveBackend.dto.request.CarRequest;
import ma.jee.luxedriveBackend.dto.response.CarResponse;
import ma.jee.luxedriveBackend.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static CarResponse CarToCarResponse(Car car){
        return modelMapper.map(car,CarResponse.class);
    }
    public static Car CarRequestToCar(CarRequest carRequest){
        return modelMapper.map(carRequest,Car.class);
    }
}
