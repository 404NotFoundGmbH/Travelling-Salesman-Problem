package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReadCSV{
    private Double[][] table=new Double[5][5];
    private String[] points=new String[5];

    public Double[][] getTable() {
        return table;
    }
    public String[] getPoints() { return points; }

    public void readCmd(String delimiter,String filepath){

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {

            // read the file line by line
            String line;
            int i=0,j=0,counter;
            while ((line = br.readLine()) != null) {

                // convert line into columns
                String[] columns = line.split(delimiter);
                if(isDouble(columns[0]) && isDouble(columns[1])){
                    for (String c: columns) {
                        table[j-1][i]=Double.parseDouble(c);
                        i++;
                    }
                }else if(!isDouble(columns[0])&&isDouble(columns[1])){           //incase the csv-file has the points also written vertically
                    for(i=1;i<columns.length-1;i++)
                        table[j][(i)-1]=Double.parseDouble(columns[i]);

                }else if(!isDouble(columns[0])&&!isDouble(columns[1])){     //incase the csv-file has point written at the first line (horizontally)
                    counter = 0;
                    for (String cities : columns) {
                        points[counter] = cities;
                        counter++;
                    }
                }
                i=0;
                j++;
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void printTable() {
        for (Double[] doubles : table) { //this equals to the row in our matrix.
            for (Double aDouble : doubles) { //this equals to the column in each row.
                System.out.print(aDouble + "  ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }
    public void printPoints() {
        for (String city: points) {
            System.out.println(city);
        }
    }
}
