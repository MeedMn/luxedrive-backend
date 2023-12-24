package ma.jee.luxedriveBackend.mapper;

import ma.jee.luxedriveBackend.dto.request.CustomerRequest;
import ma.jee.luxedriveBackend.dto.request.EmployeeRequest;
import ma.jee.luxedriveBackend.dto.request.SignupRequest;
import ma.jee.luxedriveBackend.dto.response.CustomerResponse;
import ma.jee.luxedriveBackend.entity.Customer;
import ma.jee.luxedriveBackend.entity.auth.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static CustomerRequest UserToCustomerRequest(SignupRequest signupRequest){
        return modelMapper.map(signupRequest,CustomerRequest.class);
    }
    public static EmployeeRequest UserToEmployeeRequest(SignupRequest signupRequest){
        return modelMapper.map(signupRequest,EmployeeRequest.class);
    }
}
