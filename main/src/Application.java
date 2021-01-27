
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;


import resources.*;

public class Application {
    public static void main(String[] args) {
        String delimiter=";";

        long startTime = System.nanoTime();
        ReadCSV csvin = new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        double[][] table = csvin.getdTable();

        //csvin.printTable();
        //System.out.println(" ");

        NearestNeighborV2 nearestNeighbor = new NearestNeighborV2();
        table = nearestNeighbor.executeNearestNeighbor(table,0);
        //System.out.println(Arrays.deepToString(table));
        //System.out.println("Distance: " + nearestNeighbor.getDistance(table));
        nearestNeighbor.getDistance(table);
        //csvin.printPoints();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Execution time: " + duration/1000000 + "");

    }
}
