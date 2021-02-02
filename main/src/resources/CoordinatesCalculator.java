public class CoordinatesCalculator
{
    public double[][] executeCalculator(double[][] matrix)
    {
        double[][] coords = new double[2][matrix.length];

        if(matrix.length > 3)
        {
            coords[0][0] = 0;
            coords[1][0] = 0;
            coords[0][1] = matrix[0][1];
            coords[1][1] = 0;
            coords[0][2] = (Math.pow(matrix[0][2], 2) + Math.pow(matrix[0][1], 2) - Math.pow(matrix[1][2], 2)) / (matrix[0][1] * 2);
            coords[1][2] = Math.sqrt(Math.pow(matrix[0][2], 2) - Math.pow(coords[0][2], 2));
        }

        for(int count = 3; count < matrix.length; count++)
        {
            if(Math.abs(matrix[0][count] - matrix[1][count]) < matrix[0][1] && matrix[0][1] < matrix[0][count] + matrix[1][count])
            {
                coords[0][count] = (Math.pow(matrix[0][count], 2) + Math.pow(matrix[0][1], 2) - Math.pow(matrix[1][count], 2)) / (matrix[0][1] * 2);
                coords[1][count] = Math.sqrt(Math.pow(matrix[0][count], 2) - Math.pow(coords[0][count], 2));

                if(matrix[count][count - 1] != Math.sqrt(Math.pow(coords[0][count - 1] - coords[0][count], 2) + Math.pow(coords[1][count - 1] - coords[1][count], 2)))
                    coords[1][count] *= (-1);
            }
        }
        return coords;
    }
}