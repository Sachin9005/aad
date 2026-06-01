package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.CustomerDTO;
import lk.ijse.aad.repository.CustomerRepository;
import lk.ijse.aad.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

    }
}
