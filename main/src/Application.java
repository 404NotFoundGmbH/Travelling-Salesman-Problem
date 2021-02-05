
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
        double[][] table1 = csvin.getdTable();
        double[][] table = {{0, 15, 64, 200},
                {15, 0, 60, 200.5617112013158},
                {64, 60, 0, 260.4274125362986},
                {200, 200.5617112013158,260.4274125362986, 0 }};

        //generate adjacency matrix in java
        //MappingMatrixGenerator matrixGenerator = new MappingMatrixGenerator(10);
        //double[][] table = matrixGenerator.generate();

        switch (eingabe){
            case 1:
                startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
                System.out.println("Distance: " + NearestNeighborV3.executeNearestNeighbor(table1,0));
                endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time
                duration = (endTime - startTime);
                System.out.println("That took " + duration + " milliseconds");
                System.out.println("Executed Nearest Neighbor");
            break;
            case 2:
                startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
                System.out.println("Distance: " + NearestNeighborV3.extendedNearestNeighborV2(table1));
                endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time
                duration = (endTime - startTime);
                System.out.println("That took " + duration + " milliseconds");
                System.out.println("Executed extended Nearest Neighbor");
            break;
            case 3:
                startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
                table = CoordinatesCalculator.executeCalculator(table);

                List<Point2D> points = new ArrayList<>();

                System.out.println(table.length);

                for (int i=0;i<table.length;i++) {
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
            break;
            default:
                System.out.println("Please check your entry and try again!");
            break;
        }
    }
}
