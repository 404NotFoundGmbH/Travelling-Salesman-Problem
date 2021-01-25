import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import resources.ReadCSV;

public class Application {
    public static void main(String[] args) {
        String delimiter=";";

        ReadCSV csvin=new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        Double[][] table=csvin.getTable();

        csvin.printTable();

        csvin.printPoints();

    }
}
