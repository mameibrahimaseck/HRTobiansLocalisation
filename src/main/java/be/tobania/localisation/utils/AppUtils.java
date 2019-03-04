package be.tobania.localisation.utils;

import be.tobania.localisation.model.HomeAddress;
import be.tobania.localisation.model.Customer;
import be.tobania.localisation.model.Employee;
import be.tobania.localisation.model.WorkAddress;
import be.tobania.localisation.services.CustomerService;
import be.tobania.localisation.services.EmployeeService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUtils {

    static FileUtils fileUtilsEmployee = new FileUtils<Employee>();
    static FileUtils fileUtilsCustomer = new FileUtils<Customer>();


    public static List<Employee> readFromCSVFile(String filePath, EmployeeService service){
        List<Employee> employees = readRecords(filePath, service) ;
        return employees;

    }

    public static List<Employee> readFromCSVFile(String path, EmployeeService repository, Employee employee){
        List<Employee> employees = readRecords(path,repository,employee);
        return employees;
    }

    public static List<Customer> readFromCSVFile(String path, CustomerService repository, Customer customer){
        List<Customer> customers = readRecords(path,repository,customer);
        return customers;
    }

    private static Employee setEmployeeValues(List<String> values){

        Employee employee = Employee.builder()
                .firstName(values.get(0))
                .lastName(values.get(1))
                .technology(values.get(2))
                .client(values.get(3))
                .placeOfProject(values.get(4))
                .workRegion(values.get(5))
                .postalCode(values.get(6))
                .homeRegion(getRegionFromPostalCode(values.get(6)))
                .build();

        return employee;
    }

    private static List<Employee> readRecords(String path, EmployeeService service) {

        List<Employee> employees = new ArrayList<>();

        Path source = Paths.get(path);

        if(Files.exists(source)){

            System.out.println("CSV file exist = "+Files.exists(source));

            service.truncateEmployeeTable();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1)) {

            stream.forEach(e -> {
                List<String> values =Arrays.asList(e.split(";"));
                Employee employee = setEmployeeValues(values);
                employees.add(employee);
            });

            //renameSCVFile(path);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        }

        return employees;
    }

    private static List<Employee> readRecords(String path, EmployeeService service, Employee employee){

        List<Employee> employees = new ArrayList<>();

        Path source = Paths.get(path);

        if(Files.exists(source)) {

            System.out.println("CSV file exist = " + Files.exists(source));

            service.truncateEmployeeTable();
            employees = fileUtilsEmployee.readFileRecords(path, employee);
        }

        return employees;
    }

    private static List<Customer> readRecords(String path, CustomerService repository, Customer customer){

        List<Customer> customers = new ArrayList<>();

        Path source = Paths.get(path);

        if(Files.exists(source)) {

            System.out.println("CSV file exist = " + Files.exists(source));

            repository.truncateTable();
            customers = fileUtilsCustomer.readFileRecords(path,customer);
        }

        return customers;
    }

    public static String getRegionFromPostalCode(String postalCode){
        String region;
        int pc = stringToInteger(postalCode);
        if(pc == 0)
            return "undefined";


        if(isABelgianPostalCode(pc,PostalCodeRange.BRUSSEL.get(0),PostalCodeRange.BRUSSEL.get(1)))
            region = "Bruxelles";
        else if(isABelgianPostalCode(pc,PostalCodeRange.HAL_VILVORDE.get(0),PostalCodeRange.HAL_VILVORDE.get(1)))
            region = "Hal-Vilvorde";
        else if(isABelgianPostalCode(pc,PostalCodeRange.BRABANT_WALLON.get(0),PostalCodeRange.BRABANT_WALLON.get(1)))
            region = "Brabant Wallon";
        else if(isABelgianPostalCode(pc,PostalCodeRange.BRABANT_FLAMANT.get(0),PostalCodeRange.BRABANT_FLAMANT.get(1)))
            region = "Leuven-Overijse";
        else if(isABelgianPostalCode(pc,PostalCodeRange.ANTWERP.get(0),PostalCodeRange.ANTWERP.get(1)))
            region = "Anvers";
        else if(isABelgianPostalCode(pc,PostalCodeRange.FLANDRE_OCCIDENTALE.get(0),PostalCodeRange.FLANDRE_OCCIDENTALE.get(1)))
            region = "Bruges";
        else if(isABelgianPostalCode(pc,PostalCodeRange.FLANDRE_ORIENTALE.get(0),PostalCodeRange.FLANDRE_ORIENTALE.get(1)))
            region = "Gand";
        else if(isABelgianPostalCode(pc,PostalCodeRange.HAINAUT.get(0),PostalCodeRange.HAINAUT.get(1)))
            region = "Charleroi";
        else if(isABelgianPostalCode(pc,PostalCodeRange.HAINAUT2.get(0),PostalCodeRange.HAINAUT2.get(1)))
            region = "Mons";
        else if(isABelgianPostalCode(pc,PostalCodeRange.LIEGE.get(0),PostalCodeRange.LIEGE.get(1)))
            region = "Liège";
        else if(isABelgianPostalCode(pc,PostalCodeRange.LIMBOURG.get(0),PostalCodeRange.LIMBOURG.get(1)))
            region = "Limbourg";
        else if(isABelgianPostalCode(pc,PostalCodeRange.LUXEMBOURG.get(0),PostalCodeRange.LUXEMBOURG.get(1)))
            region = "Luxembourg";
        else if(isABelgianPostalCode(pc,PostalCodeRange.NAMUR.get(0),PostalCodeRange.NAMUR.get(1)))
            region = "Namur";
        else
            region = "not Belgian postal code";


        return region;
    }

    private static boolean isABelgianPostalCode(int postalcode, int from,int to){
        return postalcode >= from && postalcode<= to;
    }

    private static int stringToInteger(String str){
        String result = str.replaceAll("\\s","");
        if(result.equals("undefined"))
            return 0;
        try {
            return Integer.valueOf(result);
        }catch (Exception e){
            System.out.println("ERROR transforming the postal code "+str +" to Integer");
            return 0;
        }
    }

   /* private static void renameSCVFile(String path){
        LocalDate ldt = LocalDate.now();
        Path source  = Paths.get(path);

        try {
            Files.move(source, source.resolveSibling(source.getFileName().toString()+ldt+".csv"));
        } catch(FileAlreadyExistsException fae) {
            fae.printStackTrace();
        } catch (IOException e) {
            // something else went wrong
            e.printStackTrace();
        }
    }*/

    public static List<Double> getCordinates(String region){
        if(region.equalsIgnoreCase("Bruxelles"))
            return Cordonates.BRUSSEL;
        else if (region.equalsIgnoreCase("Anvers"))
            return Cordonates.ANTWERP;
        else if (region.equalsIgnoreCase("Liège"))
            return Cordonates.LIEGE;
        else if (region.equalsIgnoreCase("Luxembourg"))
            return Cordonates.LUXEMBOURG;
        else if (region.equalsIgnoreCase("Namur"))
            return Cordonates.NAMUR;
        else if (region.equalsIgnoreCase("Limbourg"))
            return Cordonates.LIMBOURG;
        else if (region.equalsIgnoreCase("Hal-Vilvorde"))
            return Cordonates.HAL_VILVORDE;
        else if (region.equalsIgnoreCase("Brabant Wallon"))
            return Cordonates.BRABANT_WALLON;
        else if (region.equalsIgnoreCase("Leuven-Overijse"))
            return Cordonates.BRABANT_FLAMANT;
        else if (region.equalsIgnoreCase("Bruges"))
            return Cordonates.FLANDRE_OCCIDENTALE;
        else if (region.equalsIgnoreCase("Gand"))
            return Cordonates.FLANDRE_ORIENTALE;
        else if (region.equalsIgnoreCase("Charleroi"))
            return Cordonates.HAINAUT;
        else if (region.equalsIgnoreCase("Mons"))
            return Cordonates.HAINAUT2;
        else
            return new ArrayList<>();

    }

    public static List<Employee> getEmployeeByRegion(List<Employee> employees, String region){

        List<Employee> result = new ArrayList<>();

        employees.forEach(e -> {
            if(e.getHomeRegion().equalsIgnoreCase(region))
                result.add(e);
        });

        return result;
    }

    public static HomeAddress getPopupMessageByRegion(List<Employee> employees, String region){

        StringBuilder result = new StringBuilder();

        List<Employee> employeeList = getEmployeeByRegion(employees,region);
        result.append("<b>In "+region+" we have "+employeeList.size()+"</b><br>");

        employeeList.forEach(e -> result.append(e.getFirstName()+" "+e.getLastName()+"<br>"));

        HomeAddress address = HomeAddress.builder().region(region)
                .longitude(getCordinates(region).get(0))
                .latitude(getCordinates(region).get(1))
                .info(result.toString())
                .build();

        return address;

    }

    public static List<HomeAddress> getAllRegionInfos(List<Employee> employees, List<String> regions){

        List<HomeAddress> addresses = new ArrayList<>();

        regions.forEach(e -> addresses.add(getPopupMessageByRegion(employees,e)));

        return addresses;

    }

    private static Map<String, List<Employee>> groupByWorkPlace(List<Employee> employees){
        return employees.stream().collect(Collectors.groupingBy(Employee::getClient));
    }

    public static List<WorkAddress> getTobiansWorkAddress(List<Employee> employees, CustomerService service){
        List<WorkAddress> workAddresses = new ArrayList<>();

        Map<String, List<Employee>> stringListMap = groupByWorkPlace(employees);

        stringListMap.forEach((k, v) -> {
            //System.out.println("key :" +k+" values : "+ v);
            Customer customer = service.findByName(k);

            if(null != customer && null != customer.getName() && !customer.getName().isEmpty()){
            WorkAddress workAddress = new WorkAddress();
            workAddress.setLatitude(customer.getLatitude());
            workAddress.setLongitude(customer.getLongitude());
            workAddress.setEmployees(getStringFromList(k,v));

            workAddresses.add(workAddress);
            }
        });

        return workAddresses;
    }

    private static String getStringFromList(String str, List<Employee> employees){

        StringBuilder result = new StringBuilder();
        result.append("<b> Tobians working in "+str+"</b><br>");
        employees.forEach(e -> {
            result.append(e.getFirstName()+" "+e.getLastName()+"<br>");
        });

        return result.toString();
    }

}
