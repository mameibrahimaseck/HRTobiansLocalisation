package be.tobania.localisation.utils;

import be.tobania.localisation.model.Employee;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AppUtils {


    public static List<Employee> readFromCSVFile(String filePath){
        List<Employee> employees = readRecords(filePath) ;
        return employees;

    }

    private static Employee setEmployeeValues(List<String> values){

        Employee employee = new Employee();
        employee.setFullName(values.get(0));
        employee.setFistName(values.get(1));
        employee.setLastName(values.get(2));
        employee.setTechnology(values.get(3));
        employee.setClient(values.get(4));
        employee.setPlaceOfProject(values.get(5));
        employee.setRegion(values.get(6));

        return employee;
    }

    private static List<Employee> readRecords(String path) {

        List<Employee> employees = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1)) {

            stream.forEach(e -> {
                List<String> values =Arrays.asList(e.split(";"));
                Employee employee = setEmployeeValues(values);
                employees.add(employee);
            });

            //stream.forEach(System.out::println);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return employees;
    }

}
