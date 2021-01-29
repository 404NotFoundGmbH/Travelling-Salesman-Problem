
import java.time.Instant;

import resources.*;

public class Application {
    public static void main(String[] args) {
        String delimiter=";";



        //read from csv file
        /*
        ReadCSV csvin = new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        double[][] table = csvin.getdTable();
        */

        //generate adjacency matrix in java
        MappingMatrixGenerator matrixGenerator = new MappingMatrixGenerator(10000);
        double[][] table = matrixGenerator.generate();

        long startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
        System.out.println("Start");

        //NearestNeighbor

        NearestNeighborV2 nearestNeighbor = new NearestNeighborV2();
        //nearestNeighbor.executeNearestNeighbor(table,0);

        //extendedNearestNeighbor
        System.out.println(nearestNeighbor.extendedNearestNeighbor(table));     //prints the total distance

        long endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time

        long duration = (endTime - startTime);
        System.out.println("That took " + duration + " milliseconds");
    }
}
