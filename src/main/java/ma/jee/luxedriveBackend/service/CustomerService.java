package ma.jee.luxedriveBackend.service;

import ma.jee.luxedriveBackend.dto.request.CustomerRequest;
import ma.jee.luxedriveBackend.dto.response.CustomerResponse;
import ma.jee.luxedriveBackend.entity.Customer;
import ma.jee.luxedriveBackend.exception.EntityNotFoundException;
import ma.jee.luxedriveBackend.mapper.CustomerMapper;
import ma.jee.luxedriveBackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
}
