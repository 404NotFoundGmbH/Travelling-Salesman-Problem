package resources;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class CSV_Reader{
    public ArrayList getTable() {
        return table;
    }

    private ArrayList table=new ArrayList<String>();

    public void readCsvCmd(String delimiter){


        //parsing a CSV file into Scanner class constructor
        try
        {
            Scanner sc = new Scanner(new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv"));
            sc.useDelimiter(delimiter);   //sets the delimiter pattern
            while (sc.hasNext()){  //boolean value
                System.out.print(sc.next()+"     ");  //find and returns the next complete token from this scanner
                //table.add(sc.next());
            }
            sc.close();  //closes the scanner
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("File not found!");
        }
    }
}
