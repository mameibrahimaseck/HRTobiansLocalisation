package be.tobania.localisation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import be.tobania.localisation.model.Address;
import be.tobania.localisation.services.EmployeeService;
import be.tobania.localisation.utils.AppUtils;
import be.tobania.localisation.model.Employee;
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
        /*custStores.put(Long.valueOf(1), new Customer(new Long(1), "Jack", 25, new Address("NANTERRE CT", "77471")));
        custStores.put(Long.valueOf(2), new Customer(new Long(2), "Mary", 37, new Address("W NORMA ST", "77009")));
        custStores.put(Long.valueOf(3), new Customer(new Long(3), "Peter", 18, new Address("S NUGENT AVE", "77571")));
        custStores.put(Long.valueOf(4), new Customer(new Long(4), "Amos", 23, new Address("E NAVAHO TRL", "77449")));
        custStores.put(Long.valueOf(5), new Customer(new Long(5), "Craig", 45, new Address("AVE N", "77587")));
        custStores.put(Long.valueOf(6), new Customer(new Long(6), "Aries", 19, new Address("Broadway/Reade St, New York", "10007")));
        custStores.put(Long.valueOf(7), new Customer(new Long(7), "Brice", 39, new Address("Columbus, OH 43215, USA", "43215")));
        custStores.put(Long.valueOf(8), new Customer(new Long(8), "Cage", 24, new Address("Plano, TX 75074", "75074")));
        custStores.put(Long.valueOf(9), new Customer(new Long(9), "Ellen", 41, new Address("Modesto, CA 95354", "95354")));
        custStores.put(Long.valueOf(10), new Customer(new Long(10), "Brice", 32, new Address("Atlanta, GA 30334", "30334")));*/

        employeeService.saveEmployees(AppUtils.readFromCSVFile("./mapping_consultant_public.csv"));
    }
	 
	/*@GetMapping(value = "/all")
	public List<Customer> getResource() {
		
		List<Customer> custList = custStores.entrySet().stream()
		        .map(entry -> entry.getValue())
		        .collect(Collectors.toList());
		
		return custList;
	}*/

    @GetMapping(value = "/employee")
    public List<Employee> getAllEmployees() {

        List<Employee> employees = employeeService.findAll();

        return employees;
    }
}