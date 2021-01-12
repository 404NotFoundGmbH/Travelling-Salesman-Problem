import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import resources.CSV_Reader;

public class main {
    public static void main(String[] args) {
        String delimiter=";";
        ArrayList table=new ArrayList<String>();

        CSV_Reader read=new CSV_Reader();

        table = read.readCsvCmd(delimiter);
    }
}
