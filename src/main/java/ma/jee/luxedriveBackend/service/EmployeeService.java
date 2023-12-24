package ma.jee.luxedriveBackend.service;

import ma.jee.luxedriveBackend.dto.request.EmployeeRequest;
import ma.jee.luxedriveBackend.dto.response.EmployeeResponse;
import ma.jee.luxedriveBackend.entity.Employee;
import ma.jee.luxedriveBackend.exception.EntityNotFoundException;
import ma.jee.luxedriveBackend.mapper.EmployeeMapper;
import ma.jee.luxedriveBackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::EmployeeToEmployeeResponse).toList();
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));
        return EmployeeMapper.EmployeeToEmployeeResponse(employee);
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.EmployeeRequestToEmployee(employeeRequest);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.EmployeeToEmployeeResponse(savedEmployee);
    }

    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        return employeeRepository.findById(employeeId)
                .map(e -> {
                    e.setAddress(employeeRequest.getAddress());
                    e.setFullName(employeeRequest.getFullName());
                    e.setPhoneNumber(employeeRequest.getPhoneNumber());
                    employeeRepository.save(e);
                    return EmployeeMapper.EmployeeToEmployeeResponse(e);
                }).orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));
    }

    public String deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee", employeeId));
        employeeRepository.deleteById(employeeId);
        return "Employee Deleted";
    }
}

