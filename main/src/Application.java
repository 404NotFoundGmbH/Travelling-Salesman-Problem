import resources.*;

import java.awt.geom.Point2D;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The application to run the project
 */
public class Application {

    /**
     * The main of the program
     * @param args manual input
     */
    public static void main(String[] args) {
        long startTime, endTime, duration;


        String delimiter=";";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the algorithm:\n 1) NearestNeighbor\n 2) Extended NearestNeighbor\n 3) Convex Hull (Graham Algorithmus)\n\n");
        int eingabe = scanner.nextInt();

        //read from csv file
        ReadCSV csvin = new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        double[][] table = csvin.getdTable();

        /*
        generate adjacency matrix in java
        MappingMatrixGenerator matrixGenerator = new MappingMatrixGenerator(10);
        double[][] table = matrixGenerator.generate();
         */

        switch (eingabe) {
            case 1 -> {     // Nearest Neighbor
                startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
                System.out.println("Distance: " + NearestNeighborV3.executeNearestNeighbor(table, 0));
                endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time
                duration = (endTime - startTime);
                System.out.println("That took " + duration + " milliseconds");
                System.out.println("Executed Nearest Neighbor");
            }
            case 2 -> {     // Extended Nearest Neighbor
                startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
                System.out.println("Distance: " + NearestNeighborV3.extendedNearestNeighborV2(table));
                endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time
                duration = (endTime - startTime);
                System.out.println("That took " + duration + " milliseconds");
                System.out.println("Executed extended Nearest Neighbor");
            }
            case 3 -> {     // Convex Hull
                startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
                table = CoordinatesCalculator.executeCalculator(table);
                List<Point2D> points = new ArrayList<>();
                System.out.println(table.length);
                for (int i = 0; i < table.length; i++) {
                    points.add(i, new Point2D.Double(table[i][0], table[i][1]));
                }
                System.out.println(points);
                GrahamAlgorithmusV2.setNodes(points);
                GrahamAlgorithmusV2.computeConvexHull();
                endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time
                duration = (endTime - startTime);
                System.out.println("Distance: " + GrahamAlgorithmusV2.getFinalDistance());
                System.out.println("That took " + duration + " milliseconds");
                System.out.println("Executed Convex Hull (Graham Algorithmus)");
            }
            default -> System.out.println("Please check your entry and try again!");
        }
    }
}
