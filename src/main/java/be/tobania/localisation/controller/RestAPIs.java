package be.tobania.localisation.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import be.tobania.localisation.model.HomeAddress;
import be.tobania.localisation.model.WorkAddress;
import be.tobania.localisation.repositories.CustomerRepository;
import be.tobania.localisation.repositories.EmployeeRepository;
import be.tobania.localisation.services.CustomerService;
import be.tobania.localisation.services.EmployeeService;
import be.tobania.localisation.utils.AppUtils;
import be.tobania.localisation.model.Employee;
import be.tobania.localisation.utils.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.tobania.localisation.model.Customer;

@RestController
@RequestMapping("/api/localisation")
public class RestAPIs {
	
	//Map<Long, Customer> custStores = new HashMap<Long, Customer>();

	@Autowired
    EmployeeService employeeService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;
	
	@PostConstruct
    public void initIt() throws Exception {

        employeeService.saveEmployees(AppUtils.readFromCSVFile("./mapping_consultant_public.csv", employeeService,new Employee()));
        customerService.saveList(AppUtils.readFromCSVFile("./clients.csv", customerService, new Customer()));
    }



    @GetMapping(value = "/employee")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return employees;
    }


    @GetMapping(value="/mapInfos")
        public List<HomeAddress> getRegionsAndCordinates(){
            List<Employee> employees = employeeService.findAll();
            return AppUtils.getAllRegionInfos(employees, Regions.REGIONS);
    }

    @GetMapping(value="/workplace")
    public List<WorkAddress> getWorkAddresses(){
        List<Employee> employees = employeeService.findAll();
        return AppUtils.getTobiansWorkAddress(employees,customerService);
    }



}