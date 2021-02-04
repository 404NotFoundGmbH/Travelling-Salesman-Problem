package resources;

/**
 * this class implements the algorithm nearest neighbor
 */

public class NearestNeighborV2_1
{
    /**
     * An array is created, where each point represents a number (0 - number of points).
     * Then the startPoint with is swapped with the first position from the Array. Then
     * this value is given for the row of the matrix and the remaining positions of the
     * array are used for the column number of the matrix. Each column of the matrix[row]
     * gets compared. As soon as the point closest to the start point is found, this point
     * becomes the new start point and it starts again. But now it is swapped with the
     * second position of the array. Thus one receives at the end the order of the points,
     * as one should drive them off.
     *
     * @param matrix        Matrix with the distances between the points.
     * @param startPoint    Define the startPoint
     */
    public int[] executeNearestNeighbor(double[][] matrix, int startPoint)
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

    /**
     * Number pos2 is passed and returned to pos1, then pos1 defined as pos1 = pos2.
     *
     * @param pos2  Second position
     * @param pos1  First position
     * @return      Second position
     */
    private int swap(int pos2, int pos1) { return  pos2; }
}
