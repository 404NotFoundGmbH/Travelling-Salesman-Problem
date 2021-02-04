package resources;

/**
 * This class implements the algorithm nearest neighbour
 */
public class NearestNeighborV2
{
    /**
     * At the beginning, the diagonal with the zeros is replaced by a number in the
     * adjacency matrix. Each point then has a number. Then the next point is placed
     * in the last position and all distances to this point are compared and the
     * shortest becomes the new starting point. This is done until a route is created.
     * The route is then placed in the adjacency matrix in place of the zeros.
     *
     * @param matrix        Matrix with the distances between the points.
     * @param startPoint    Define the startPoint
     */

    public double[][] executeNearestNeighbor(double[][] matrix, int startPoint)
    {
        double shortestWay;

        for(int count = 0; count < matrix.length; count++)
            matrix[count][count] = count;

        for(int countRow = 1; countRow < matrix.length; countRow++)
        {
            matrix[startPoint][startPoint] = swap(matrix[matrix.length - countRow][matrix.length - countRow], matrix[matrix.length - countRow][matrix.length - countRow] = matrix[startPoint][startPoint]);

            shortestWay = matrix[(int) matrix[matrix.length - countRow][matrix.length - countRow]][(int) matrix[0][0]];
            startPoint = 0;

            for(int countColumn = 1; countColumn < matrix.length - countRow; countColumn++)
                if(shortestWay > matrix[(int) matrix[matrix.length - countRow][matrix.length - countRow]][(int) matrix[countColumn][countColumn]])
                {
                    shortestWay = matrix[(int) matrix[matrix.length - countRow][matrix.length - countRow]][(int) matrix[countColumn][countColumn]];
                    startPoint = countColumn;
                }
        }
        //print(matrix);
        return matrix;
    }

    /**
     * Number pos2 is passed and returned to pos1, then pos1 defined as pos1 = pos2.
     *
     * @param pos2  Second position
     * @param pos1  First position
     * @return      First position
     */
    private double swap(double pos2, double pos1) { return  pos2; }

    public double getDistance (double[][] matrix){

        double distance = 0.0;
        for (int i = (matrix.length-1); i > 0; i--){
            int f = (int) matrix[i][i];
            int e = (int) matrix[i-1][i-1];
            distance += matrix[f][e];
        }

        return distance;
    }

    public double extendedNearestNeighbor(double[][] matrix){
        double[][] matrix1;
        double distance = Double.MAX_VALUE;
        double shortestDistance;
        for(int i=0; i< matrix.length;i++) {
            matrix1 = executeNearestNeighbor(matrix,i);
            shortestDistance = getDistance(matrix1);
            if (shortestDistance <= distance)
                distance = shortestDistance;
        }


        //System.out.println(Arrays.toString(distance));
        return distance;
    }

    /**
     * Prints the matrix diagonal backwards. To see the path
     *
     * @param matrix matrix with the Way in it
     */
    /*private void print(double[][] matrix)
    {
        for (int count = matrix.length - 1; count >= 0; count--)
            System.out.print(matrix[count][count] + " ");
        System.out.println();
    }*/
}