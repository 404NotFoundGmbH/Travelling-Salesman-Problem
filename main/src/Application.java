
import java.awt.*;
import java.awt.geom.Point2D;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.Scanner;

import resources.*;

import javax.swing.*;

public class Application {


    public static void main(String[] args) {
        double[][] matrix = {{0, 15, 64, 200},
                {15, 0, 60, 200.5617112013158},
                {64, 60, 0, 260.4274125362986},
                {200, 200.5617112013158,260.4274125362986, 0 }};
        String delimiter=";";

        System.out.println("Suche den Algorithmus aus:\n1. NearestNeighbor\n2. Extended Nearestneighbor\n3. Beide\n\n");

        Scanner sc2 = new Scanner(System.in);
        int eingabe = sc2.nextInt();


        //read from csv file

        ReadCSV csvin = new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        double[][] table = csvin.getdTable();




        //generate adjacency matrix in java
        //MappingMatrixGenerator matrixGenerator = new MappingMatrixGenerator(10);
        //double[][] table = matrixGenerator.generate();

/*
        matrix = CoordinatesCalculator.executeCalculator(matrix);
        System.out.println(Arrays.deepToString(matrix));


        List<Point2D> points = new ArrayList<>();
        Point2D bo = new Point();

        for (int i=0;i< matrix.length;i++) {
            bo.setLocation(matrix[i][0], matrix[i][1]);
            points.add(bo);
        }

        GrahamAlgorithmusV2.setNodes(points);
        System.out.println(GrahamAlgorithmusV2.computeConvexHull());

 */

        //long startTime = Instant.now().toEpochMilli();      //get current time - used to measure execution time
        //System.out.println("Start");



        //Scanner sc = new Scanner(System.in);
        //System.out.println();



        /*
        String eingabe=JOptionPane.showInputDialog(f,"Pls Input your algorythm:");
        int eingabe2= Integer.parseInt(eingabe);

         */
        System.out.println("Eingabe: " + eingabe);


        //int eingabe = sc.nextInt();
        //sc.close();
        NearestNeighborV2 nearestNeighbor = new NearestNeighborV2();

        switch (eingabe){
            case 1:
                table = nearestNeighbor.executeNearestNeighbor(table,0);
                System.out.println("Diastance NearestNeighbor: " + nearestNeighbor.getDistance(table));
            break;
            case 2:
                System.out.println("Distance extended NearestNeighbor" + nearestNeighbor.extendedNearestNeighbor(table));
            break;
            case 3:
                table = nearestNeighbor.executeNearestNeighbor(table,0);
                System.out.println("Diastance NearestNeighbor: " + nearestNeighbor.getDistance(table));
                System.out.println("Distance extended NearestNeighbor" + nearestNeighbor.extendedNearestNeighbor(table));
        }
        //NearestNeighbor


        //extendedNearestNeighbor
        //System.out.println(nearestNeighbor.extendedNearestNeighbor(table));     //prints the total distance

        //long endTime = Instant.now().toEpochMilli();    //get current time - used to measure execution time

        //long duration = (endTime - startTime);
        //System.out.println("That took " + duration + " milliseconds");


    }
}
