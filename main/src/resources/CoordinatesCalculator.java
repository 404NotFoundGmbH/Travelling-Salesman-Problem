package resources;

/**
 * this class calculates the coordinates out of an adjacency matrix
 */
public class CoordinatesCalculator
{
    /**
     *The 2d array coords is defined. It will then contain the values for x and y
     * of the respective points. Here coords[0] is for all x-coordinates of the points
     * and coords[1] for all y-coordinates. At the beginning the point 0 is set on the
     * coordinate origin. Then the point 1 is set on X(distance between 0 and 1) and 0.
     * With two chosen points you can now calculate a new point based on the distances.
     * This is done by calculating the intersection of two circles. These circles have
     * once the center point point 0 and point 1. Then one has the 3rd point. Now you
     * can determine the 4th point by repeating the procedure, but now you have to check
     * if the value is above the x-axis or below, because circles usually have 2 intersection
     * points. Therefore one compares with the distance formula the first intersection point
     * with the matrix if this does not agree the Y value of the point * (-1) is calculated.
     *
     * @param matrix  Matrix with the distances between the points.
     * @return coords 2d array with the respective x and y values of the points
     */
    public static double[][] executeCalculator(double[][] matrix)
    {
        double[][] coords = new double[matrix.length][2];

        if(matrix.length > 3)
        {
            coords[0][0] = 0;
            coords[0][1] = 0;
            coords[1][0] = matrix[0][1];
            coords[1][1] = 0;
            coords[2][0] = (Math.pow(matrix[0][2], 2) + Math.pow(matrix[0][1], 2) - Math.pow(matrix[1][2], 2)) / (matrix[0][1] * 2);
            coords[2][1] = Math.sqrt(Math.pow(matrix[0][2], 2) - Math.pow(coords[2][0], 2));
        }

        for(int count = 3; count < matrix.length; count++)
        {
            if(Math.abs(matrix[0][count] - matrix[1][count]) < matrix[0][1] && matrix[0][1] < matrix[0][count] + matrix[1][count])
            {
                coords[count][0] = (Math.pow(matrix[0][count], 2) + Math.pow(matrix[0][1], 2) - Math.pow(matrix[1][count], 2)) / (matrix[0][1] * 2);
                coords[count][1] = Math.sqrt(Math.pow(matrix[0][count], 2) - Math.pow(coords[count][0], 2));

                if(matrix[count][count - 1] != Math.sqrt(Math.pow(coords[count - 1][0] - coords[count][0], 2) + Math.pow(coords[count - 1][1] - coords[count][1], 2)))
                    coords[count][1] *= (-1);
            }
            else
            {
                coords = null;
                break;
            }

        }
        return coords;
    }
}
