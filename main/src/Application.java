import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import resources.ReadCSV;

public class Application {
    public static void main(String[] args) {
        String delimiter=";";
        String filepath="  insert your filepath here...  ";

        ReadCSV csvin=new ReadCSV();

        csvin.readCmd(delimiter,filepath);
        Double[][] table=csvin.getTable();

        csvin.printTable();

        csvin.printPoints();

    }
}
