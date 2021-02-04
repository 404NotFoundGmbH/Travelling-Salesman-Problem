package resources;

public class NearestNeighborV3
{
    public static int[] extendedNearestNeighborV2(double[][] matrix)
    {
        int number = executeModifiedNearestNeighbor(matrix);
        return executeNearestNeighbor(matrix, number);
    }

    private static int executeModifiedNearestNeighbor(double[][] matrix)
    {
        int[] path = new int[matrix.length];
        double shortestWay, bestPath = Double.MAX_VALUE, pathLength = 0;
        int startPoint, number = 0;

        for(int countPoints = 0; countPoints < matrix.length; countPoints++)
        {
            startPoint = countPoints;
            pathLength = 0;

            for(int count = 0; count < matrix.length; count++)
                path[count] = count;

            for(int countRow = 0; countRow < matrix.length - 1; countRow++)
            {
                path[startPoint] = swap(path[countRow], path[countRow] = path[startPoint]);

                shortestWay = matrix[path[countRow]][path[matrix.length - 1]];
                startPoint = matrix.length - 1;

                for(int countColumn = matrix.length - 1; countColumn > countRow; countColumn--)
                    if(shortestWay > matrix[path[countRow]][path[countColumn]])
                    {
                        shortestWay = matrix[path[countRow]][path[countColumn]];
                        startPoint = countColumn;
                    }
                pathLength += shortestWay;
            }
            pathLength += matrix[path[0]][path[matrix.length - 1]];

            if(bestPath > pathLength)
            {
                bestPath = pathLength;
                number = countPoints;
            }
        }
        return number;
    }

    public static int[] executeNearestNeighbor(double[][] matrix, int startPoint)
    {
        int[] path = new int[matrix.length];
        double shortestWay;

        for(int count = 0; count < matrix.length; count++)
            path[count] = count;

        for(int countRow = 0; countRow < matrix.length - 1; countRow++)
        {
            path[startPoint] = swap(path[countRow], path[countRow] = path[startPoint]);

            shortestWay = matrix[path[countRow]][path[matrix.length - 1]];
            startPoint = matrix.length - 1;

            for(int countColumn = matrix.length - 1; countColumn > countRow; countColumn--)
                if(shortestWay > matrix[path[countRow]][path[countColumn]])
                {
                    shortestWay = matrix[path[countRow]][path[countColumn]];
                    startPoint = countColumn;
                }
        }
        return path;
    }

    private static int swap(int pos2, int pos1) { return pos2; }

    public static double getDistance(double[][] matrix, int[] points)
    {
        double distance = matrix[points[0]][points[matrix.length - 1]];

        for(int count = 1; count < matrix.length; count++)
            distance += matrix[points[count - 1]][points[count]];
        
        return distance;
    }
}