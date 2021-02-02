package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Reads a csv-file and returns it as needed!
 * @author Valentin Gabriel
 */
public class ReadCSV {
    private Double[][] table;
    private double[][] dTable;  //für Nearest Neighbor
    private String[] points;
    private String filepath;
    private final String delimiter;

    /**
     * Constructor of ReadCSV
     * @param delimiter
     */
    public ReadCSV(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * asks to input a filepath via cmd and checks it!
     */
    public void setFilepath() {
        System.out.println("Input Filepath:");
        Scanner sc = new Scanner(System. in );
        filepath = sc.nextLine();
        sc.close();

        this.filepath = checkFilePath(filepath);

    }

    public Double[][] getTable() {
        return table;
    }
    public double[][] getdTable(){
        return dTable;
    }

    public String[] getPoints() {
        return points;
    }

    /**
     * uses a Bufferedreader with the standard buffersize to read a csv-file and parse it.
     */
    public void readCmd() {
        int numTokens;
        if(filepath!=null) {
            System.out.println("Reading Files from:\n" + filepath + "\n");
            try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {        //BufferedReader initialized
                // read the file line by line
                String line;
                numTokens = countPoints();          //count how many "Points" the file has

                dTable=new double[numTokens][numTokens];
                table = new Double[numTokens][numTokens];
                points = new String[numTokens];

                int i = 0, j = 0, counter = 0;
                while ((line = br.readLine()) != null) {            //loop through the file and check if file has ended

                    // convert line into columns
                    String[] columns = line.split(delimiter);       //splits a line, with a given delimiter

                    if (!isDouble(columns[0]) && !isDouble(columns[1])) {       //if the first line consists of Names,store them in a separate array.
                        for (String p : columns) {
                            points[i] = p;
                            i++;
                        }
                        j = -1;
                    }

                    if (isDouble(columns[0]) && isDouble(columns[1])) { //No Names given
                        for (String c : columns) {
                            table[j][i] = Double.parseDouble(c);
                            dTable[j][i] = Double.parseDouble(c);
                            i++;
                        }
                    } else if (!isDouble(columns[0]) && isDouble(columns[1])) {         //Names also given horizontally
                        for (String c : columns) {
                            if (i != 0) {
                                table[j][i - 1] = Double.parseDouble(c);
                                dTable[j][i - 1] = Double.parseDouble(c);
                            }
                            i++;
                        }
                    }
                    i = 0;
                    j++;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex) {
                System.err.println();
            }
            if(points[0]==null){
                for (int i = 0; i < points.length; i++) {
                    points[i]="Point" +(i+1);
                }
            }
        }


    }

    /**
     * counts the number of points in a given file.
     * @return numTokens
     */
    public int countPoints() {
        int numTokens = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {

            // read the file line by line
            String line;
            numTokens = br.readLine().split(delimiter).length;
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return numTokens;

    }

    /**
     * returns filepath, if its valid!
     * returns null, if its unvalid!
     * checks if the filepath == null
     * @param filepath
     * @return filepath or null
     */
    public String checkFilePath(String filepath) {
        File file = null;
        if (this.filepath != null) file = new File(this.filepath);

        try {
            if (isValidFile(file)) {
                return this.filepath;
            } else {
                System.err.println("\nFile not Valid or not accessible!");
            }
        } catch(IOException ex) {
            //System.err.println("InputError");       //when does this print?
        }
        return null;
    }

    /**
     * checks if a file is a readable csv-file->return true
     * if file is not one of the above->return false
     * @param file
     * @return boolean
     * @throws IOException
     */
    public boolean isValidFile(File file) throws IOException {
        //if given file is null return false
        if (file != null) {

            //now we check if given file is valid file (file or folder)
            // else we check if we can make a directory with path of given file.
            //Thus we validate that is a valid path.
            //after the following step a new file will be created if it return true, so it is a valid file path.
            return file.isFile() && (file.getName().contains(".csv"))&&file.canRead();

        }
        return false;
    }

    /**
     * checks if a given string can be parsed into a double
     * can be parsed->return true
     * cannot be parsed->return false
     * @param str
     * @return boolean
     */
    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * prints table
     */
    public void printTable() {
        if(table!=null)
            for (Double[] doubles: table) { //this equals to the row in our matrix.
                for (Double aDouble: doubles) { //this equals to the column in each row.
                    System.out.print(aDouble + "  ");
                }
                System.out.println(); //change line on console as row comes to end in the matrix.
            }
    }
    public void printdTable() {
        if(dTable!=null)
            for (double[] doubles: dTable) { //this equals to the row in our matrix.
                for (double aDouble: doubles) { //this equals to the column in each row.
                    System.out.print(aDouble + "  ");
                }
                System.out.println(); //change line on console as row comes to end in the matrix.
            }
    }
    /**
     * prints points
     */
    public void printPoints() {
        if(points!=null)
            for (String city: points) {
                System.out.println(city);
            }
    }
}
