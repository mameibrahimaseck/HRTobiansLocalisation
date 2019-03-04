package be.tobania.localisation.services.impl;

import be.tobania.localisation.model.Customer;
import be.tobania.localisation.repositories.CustomerRepository;
import be.tobania.localisation.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public void save(Customer customer) {

    }

    @Override
    @Transactional
    public void saveList(List<Customer> customers) {
        customers.forEach(e -> customerRepository.save(e));
    }

    @Override
    @Transactional
    public void truncateTable() {
        customerRepository.truncateTable();
    }

    @Override
    @Transactional
    public Customer findByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers :: add);
        return customers;
    }
}
