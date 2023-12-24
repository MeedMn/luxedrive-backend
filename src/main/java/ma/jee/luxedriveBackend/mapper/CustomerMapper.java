package ma.jee.luxedriveBackend.mapper;

import ma.jee.luxedriveBackend.dto.request.CustomerRequest;
import ma.jee.luxedriveBackend.dto.response.CustomerResponse;
import ma.jee.luxedriveBackend.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static CustomerResponse CustomerToCustomerResponse(Customer customer){
        return modelMapper.map(customer,CustomerResponse.class);
    }
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return modelMapper.map(customerRequest,Customer.class);
    }
}
