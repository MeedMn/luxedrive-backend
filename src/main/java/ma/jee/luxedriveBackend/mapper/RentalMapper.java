package ma.jee.luxedriveBackend.mapper;

import ma.jee.luxedriveBackend.dto.request.CarRequest;
import ma.jee.luxedriveBackend.dto.request.RentalRequest;
import ma.jee.luxedriveBackend.dto.response.CarResponse;
import ma.jee.luxedriveBackend.dto.response.RentalResponse;
import ma.jee.luxedriveBackend.entity.Car;
import ma.jee.luxedriveBackend.entity.Rental;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static RentalResponse RentalToRentalResponse(Rental rental){
        return modelMapper.map(rental,RentalResponse.class);
    }
    public static Rental RentalRequestToRental(RentalRequest rentalRequest){
        return modelMapper.map(rentalRequest,Rental.class);
    }
}
