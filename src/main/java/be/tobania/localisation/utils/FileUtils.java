package be.tobania.localisation.utils;

import be.tobania.localisation.model.Customer;
import be.tobania.localisation.model.Employee;
import org.springframework.data.repository.CrudRepository;

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


public class FileUtils<E> {



    public List<E> readFileRecords(String path , E e){

        List<E> objects = new ArrayList<>();

        Path source = Paths.get(path);



            try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.ISO_8859_1)) {

                stream.forEach(x -> {
                    List<String> values = Arrays.asList(x.split(";"));

                    E obj = null;
                    try {
                        obj = setOjectValues(values, e.getClass().newInstance());
                    } catch (InstantiationException | IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                    objects.add(obj);
                });

                renameSCVFile(path);

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }


        return objects;

    }

    private E setOjectValues(List<String> values, Object e){



        if(e instanceof Employee){

            ((Employee) e).setFirstName(values.get(0));
            ((Employee) e).setLastName(values.get(1));
            ((Employee) e).setTechnology(values.get(2));
            ((Employee) e).setClient(values.get(3));
            ((Employee) e).setPlaceOfProject(values.get(4));
            ((Employee) e).setWorkRegion(values.get(5));
            ((Employee) e).setPostalCode(values.get(6));
            ((Employee) e).setHomeRegion(AppUtils.getRegionFromPostalCode(values.get(6)));

        }
        if(e instanceof Customer){
            ((Customer) e).setName(values.get(0));
            ((Customer) e).setLatitude(Double.valueOf(values.get(1)));
            ((Customer) e).setLongitude(Double.valueOf(values.get(2)));

        }

        return (E) e;

    }

    private static void renameSCVFile(String path){
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
    }

}
