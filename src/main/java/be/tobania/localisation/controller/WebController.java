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

    @GetMapping(value="/map")
    public String getMapPage(Model model){
       // List<Employee> employees = employeeService.findAll();

        //model.addAttribute("anvers", AppUtils.getPopupMessageByRegion(employees, "Anvers"));
        //model.addAttribute("bxl", AppUtils.getPopupMessageByRegion(employees, "Bruxelles"));
        //model.addAttribute("hasselt", AppUtils.getPopupMessageByRegion(employees, "Hasselt"));

	    return "mapPage";
    }





}