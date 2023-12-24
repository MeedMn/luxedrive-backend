package ma.jee.luxedriveBackend.mapper;

import ma.jee.luxedriveBackend.dto.request.EmployeeRequest;
import ma.jee.luxedriveBackend.dto.response.EmployeeResponse;
import ma.jee.luxedriveBackend.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static EmployeeResponse EmployeeToEmployeeResponse(Employee employee){
        return modelMapper.map(employee,EmployeeResponse.class);
    }
    public static Employee EmployeeRequestToEmployee(EmployeeRequest employeeRequest){
        return modelMapper.map(employeeRequest,Employee.class);
    }
}
