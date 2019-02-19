package be.tobania.localisation.utils;

import be.tobania.localisation.model.Employee;
import be.tobania.localisation.services.EmployeeService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AppUtils {


    public static List<Employee> readFromCSVFile(String filePath, EmployeeService service){
        List<Employee> employees = readRecords(filePath, service) ;
        return employees;

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

            renameSCVFile(path);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        }

        return employees;
    }

    private static String getRegionFromPostalCode(String postalCode){
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
            region = "Louvain -  Overijse";
        else if(isABelgianPostalCode(pc,PostalCodeRange.ANTWERP.get(0),PostalCodeRange.ANTWERP.get(1)))
            region = "Anvers";
        else if(isABelgianPostalCode(pc,PostalCodeRange.FLANDRE_OCCIDENTALE.get(0),PostalCodeRange.FLANDRE_OCCIDENTALE.get(1)))
            region = "Bruges - Flandre Occidentale";
        else if(isABelgianPostalCode(pc,PostalCodeRange.FLANDRE_ORIENTALE.get(0),PostalCodeRange.FLANDRE_ORIENTALE.get(1)))
            region = "Gand - Flandre Orientale";
        else if(isABelgianPostalCode(pc,PostalCodeRange.HAINAUT.get(0),PostalCodeRange.HAINAUT.get(1)))
            region = "Charleroi  - Hainaut";
        else if(isABelgianPostalCode(pc,PostalCodeRange.HAINAUT2.get(0),PostalCodeRange.HAINAUT2.get(1)))
            region = "Mons - Hainaut(2)";
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

    private static void renameSCVFile(String path){
        LocalDate ldt = LocalDate.now();
        Path source  = Paths.get(path);

        try {
            Files.move(source, source.resolveSibling("mapping_consultant_public_"+ldt+".csv"));
        } catch(FileAlreadyExistsException fae) {
            fae.printStackTrace();
        } catch (IOException e) {
            // something else went wrong
            e.printStackTrace();
        }
    }

}
