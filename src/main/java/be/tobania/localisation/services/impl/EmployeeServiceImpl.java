package be.tobania.localisation.services.impl;

import be.tobania.localisation.services.EmployeeService;
import be.tobania.localisation.model.Employee;
import be.tobania.localisation.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void saveEmployees(List<Employee> employees) {
        employees.forEach(e -> employeeRepository.save(e));
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees :: add);
        return employees;
    }
}
