package be.tobania.localisation.services;

import be.tobania.localisation.model.Employee;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(Employee employee);
    void saveEmployees(List<Employee> employees);
    List<Employee> findAll();
}
