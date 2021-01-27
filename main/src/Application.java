
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import resources.*;

public class Application {
    public static void main(String[] args) {
        String delimiter=";";

        long startTime = Instant.now().toEpochMilli();

        ReadCSV csvin = new ReadCSV(delimiter);
        csvin.setFilepath();
        csvin.readCmd();
        double[][] table = csvin.getdTable();

        NearestNeighborV2 nearestNeighbor = new NearestNeighborV2();
        System.out.println(nearestNeighbor.extendedNearestNeighbor(table));

        long endTime = Instant.now().toEpochMilli();

        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("That took " + duration/2 + " milliseconds");

    }
}
