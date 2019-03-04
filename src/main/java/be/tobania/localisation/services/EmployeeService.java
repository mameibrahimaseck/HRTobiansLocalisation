package be.tobania.localisation.services;

import be.tobania.localisation.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void saveEmployee(Employee employee);
    void saveEmployees(List<Employee> employees);
    List<Employee> findAll();
    void truncateEmployeeTable();
}
