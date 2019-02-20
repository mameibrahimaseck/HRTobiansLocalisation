package be.tobania.localisation.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import be.tobania.localisation.model.Address;
import be.tobania.localisation.services.EmployeeService;
import be.tobania.localisation.utils.AppUtils;
import be.tobania.localisation.model.Employee;
import be.tobania.localisation.utils.Regions;
import org.json.JSONArray;
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
	
	@PostConstruct
    public void initIt() throws Exception {

        employeeService.saveEmployees(AppUtils.readFromCSVFile("./mapping_consultant_public.csv", employeeService));
    }



    @GetMapping(value = "/employee")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return employees;
    }


    @GetMapping(value="/mapInfos")
        public List<Address> getRegionsAndCordinates(){
            List<Employee> employees = employeeService.findAll();
            return AppUtils.getAllRegionInfos(employees, Regions.REGIONS);
    }



}