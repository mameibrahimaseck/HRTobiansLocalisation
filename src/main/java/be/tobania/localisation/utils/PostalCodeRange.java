package be.tobania.localisation.utils;

import java.util.Arrays;
import java.util.List;

public interface PostalCodeRange {

    List<Integer> BRUSSEL = Arrays.asList(1000,1299);
    List<Integer> BRABANT_WALLON = Arrays.asList(1300,1499);
    List<Integer> BRABANT_FLAMANT = Arrays.asList(3000,3499);
    List<Integer> HAL_VILVORDE = Arrays.asList(1500,1999);
    List<Integer> ANTWERP = Arrays.asList(2000,2999);
    List<Integer> LIMBOURG = Arrays.asList(3500,3999);
    List<Integer> LIEGE = Arrays.asList(4000,4999);
    List<Integer> NAMUR = Arrays.asList(5000,5999);
    List<Integer> HAINAUT = Arrays.asList(6000,6599);
    List<Integer> LUXEMBOURG = Arrays.asList(6600,6999);
    List<Integer> HAINAUT2 = Arrays.asList(7000,7999);
    List<Integer> FLANDRE_OCCIDENTALE = Arrays.asList(8000,8999);
    List<Integer> FLANDRE_ORIENTALE = Arrays.asList(9000,9999);

}
