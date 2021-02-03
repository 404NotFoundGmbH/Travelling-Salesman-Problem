
import java.awt.*;
import java.awt.geom.Point2D;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import resources.*;

public class Application {


    public static void main(String[] args) {
        double[][] matrix = {{0, 15, 64, 200},
                {15, 0, 60, 200.5617112013158},
                {64, 60, 0, 260.4274125362986},
                {200, 200.5617112013158,260.4274125362986, 0 }};
        String delimiter=";";



        //read from csv file
        /*
        ReadCSV csvin = new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        double[][] table = csvin.getdTable();
         */



        //generate adjacency matrix in java
        //MappingMatrixGenerator matrixGenerator = new MappingMatrixGenerator(10);
        //double[][] table = matrixGenerator.generate();


        matrix = CoordinatesCalculator.executeCalculator(matrix);
        System.out.println(Arrays.deepToString(matrix));


        List<Point2D> points = new ArrayList<>();
        Point2D bo = new Point();

        for (int i=0;i< matrix.length;i++) {
            bo.setLocation(matrix[i][0], matrix[i][1]);
            points.add(bo);
        }

        GrahamAlgorithmusV2.allNodes = points;
        System.out.println(GrahamAlgorithmusV2.computeConvexHull());
/*
        long startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
        System.out.println("Start");

        //NearestNeighbor
        NearestNeighborV2 nearestNeighbor = new NearestNeighborV2();
        //nearestNeighbor.executeNearestNeighbor(table,0);

        //extendedNearestNeighbor
        System.out.println(nearestNeighbor.extendedNearestNeighbor(matrix));     //prints the total distance

        long endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time

        long duration = (endTime - startTime);
        System.out.println("That took " + duration + " milliseconds");

 */
    }
}
