package resources;

public class NearestNeighborCoordsExtended
{
    public static double[][] CordinatesNearestNeighborExtended(double[][] coords)
    {
        double shortestDistance, storage = Double.MAX_VALUE;

        int safe = 0, shortestPath = 0;

        double[][] coordsSafe = new double[coords.length][2];
        double route[][] = new double[coords.length][2];
        for(int swap = 0; swap < coords.length; swap++)
        {
            route[swap][0] = coords[swap][0];
            route[swap][1] = coords[swap][1];
            coordsSafe[swap][0] = coords[swap][0];
            coordsSafe[swap][1] = coords[swap][1];
        }

        for(int count = 0; count < coords.length; count++)
        {
            int search = 0;
            while(coordsSafe[search][0] != coordsSafe[count][0] && coordsSafe[search][1] != coordsSafe[count][1])
                search++;

            coords[search][0] = swap(coords[0][0], coords[0][0] = coords[search][0]);
            coords[search][1] = swap(coords[0][1], coords[0][1] = coords[search][1]);

            for(int countRow = 1; countRow < coords.length; countRow++)
            {
                shortestPath = 0;
                shortestDistance = Double.MAX_VALUE;
                for(int countColumn = coords.length - 1; countColumn > countRow - 1; countColumn--)
                {
                    if(shortestDistance > Math.sqrt(Math.pow(coords[countColumn][0] - coords[countRow - 1][0], 2) + Math.pow(coords[countColumn][1] - coords[countRow - 1][1], 2)))
                    {
                        shortestDistance = Math.sqrt(Math.pow(coords[countColumn][0] - coords[countRow - 1][0], 2) + Math.pow(coords[countColumn][1] - coords[countRow - 1][1], 2));
                        safe = countColumn;
                    }
                }
                coords[safe][0] = swap(coords[countRow][0], coords[countRow][0] = coords[safe][0]);
                coords[safe][1] = swap(coords[countRow][1], coords[countRow][1] = coords[safe][1]);
                shortestPath += shortestDistance;
            }
            shortestPath += Math.sqrt(Math.pow(coords[coords.length - 1][0] - coords[0][0], 2) + Math.pow(coords[coords.length - 1][1] - coords[0][1], 2));
            if(storage > shortestPath)
            {
                storage = shortestPath;
                for(int counting = 0; counting < coords.length; counting++)
                {
                    route[counting][0] = coords[counting][0];
                    route[counting][1] = coords[counting][1];
                }
            }

        }
        return coords;
    }

    private static double swap(double pos2, double pos1) { return pos2; }

    public static double getDistance(double[][] coords)
    {
        double length = Math.sqrt(Math.pow(coords[0][0] - coords[coords.length - 1][0], 2) + Math.pow(coords[0][1] - coords[coords.length - 1][1], 2));

        for(int count = 1; count < coords.length; count++)
            length += Math.sqrt(Math.pow(coords[count][0] - coords[count - 1][0], 2) + Math.pow(coords[count][1] - coords[count - 1][1], 2));

        return length;
    }
}