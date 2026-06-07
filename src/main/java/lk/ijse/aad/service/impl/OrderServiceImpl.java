package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.SaveOrderDTO;
import lk.ijse.aad.entity.Customer;
import lk.ijse.aad.entity.Order;
import lk.ijse.aad.repository.CustomerRepository;
import lk.ijse.aad.repository.OrderRepository;
import lk.ijse.aad.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveOrder(SaveOrderDTO orderDTO) {
        try {
            Order order = new Order();
            order.setDescription(orderDTO.getDescription());
            order.setTotal(orderDTO.getTotal());
            Optional<Customer> customerOpt = customerRepository.findById(orderDTO.getCustomerId());
            if (customerOpt.isEmpty()) {
                throw new RuntimeException("Customer not found with ID: " + orderDTO.getCustomerId());
            }
            order.setCustomer(customerOpt.get());
            orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
