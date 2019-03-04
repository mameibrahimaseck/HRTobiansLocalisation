package be.tobania.localisation.controller;

import be.tobania.localisation.model.Employee;
import be.tobania.localisation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping(value="/homeMap")
    public String getHomeMapPage(){
	    return "mapHomePage";
    }

    @GetMapping(value="/workMap")
    public String getWorkMapPage(){
        return "mapWorkPage";
    }



}