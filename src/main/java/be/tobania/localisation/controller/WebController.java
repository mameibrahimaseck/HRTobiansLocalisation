package be.tobania.localisation.controller;

import be.tobania.localisation.model.Employee;
import be.tobania.localisation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    EmployeeService employeeService;
    private List<Employee> employees;
    private String region;

    @GetMapping(value="/")
    public String homepage(){
        return "index";
    }

    @GetMapping(value="/map")
    public String getMapPage(Model model){
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("anvers", getPopupMessageByRegion(employees, "Anvers"));
        model.addAttribute("bxl", getPopupMessageByRegion(employees, "Bruxelles"));
        model.addAttribute("hasselt", getPopupMessageByRegion(employees, "Hasselt"));

	    return "mapPage";
    }

    private List<Employee> getEmployeeByRegion(List<Employee> employees, String region){
        this.employees = employees;
        this.region = region;
        List<Employee> result = new ArrayList<>();

	    employees.forEach(e -> {
	        if(e.getHomeRegion().equalsIgnoreCase(region))
	            result.add(e);
        });

	    return result;
    }

    private String getPopupMessageByRegion(List<Employee> employees, String region){
	    StringBuilder result = new StringBuilder();

        List<Employee> employeeList = getEmployeeByRegion(employees,region);
        result.append("<b>In "+region+" we have "+employeeList.size()+"<b><br>");

        employeeList.forEach(e -> result.append(e.getFirstName()+" "+e.getLastName()+"<br>"));

	    return result.toString();

    }


}