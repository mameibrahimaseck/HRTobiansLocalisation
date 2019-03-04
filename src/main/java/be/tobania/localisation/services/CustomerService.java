package be.tobania.localisation.services;

import be.tobania.localisation.model.Customer;

import java.util.List;

public interface CustomerService{


    void save(Customer customer);

    void saveList(List<Customer> customers);

    void truncateTable();

    Customer findByName(String name);

    List<Customer> findAll();
}
