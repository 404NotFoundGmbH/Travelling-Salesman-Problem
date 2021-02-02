public class CoordinatesConvert
{
    public static void main(String[] args)
    {
        double[][] coords = {{5,6},
                             {5,8},
                             {34, 4},
                             {4,23},
                             {5, 23},
                             {1, 4}};
        double[][] test = convert(coords);

        for(int count = 0; count < test.length; count++)
        {
            for(int count1 = 0; count1 < test.length; count1++)
                System.out.print(test[count][count1] + " ");
            System.out.println();
        }
    }

    /**
     *
     * @param coords to make a matrix
     * @return matrix
     */
    private static double[][] convert(double[][] coords)
    {
        double[][] matrix = new double[coords.length][coords.length];

        for(int countRow = 0; countRow < matrix.length; countRow++)
        {
            for(int countColumn = countRow; countColumn < matrix.length; countColumn++)
            {
                if(countColumn != countRow)
                {
                    matrix[countRow][countColumn] = Math.sqrt(Math.pow(coords[countRow][0] - coords[countColumn][0], 2) + Math.pow(coords[countRow][1] - coords[countColumn][1], 2));
                    matrix[countColumn][countRow] = matrix[countRow][countColumn];
                }
                else
                    matrix[countRow][countColumn] = 0;

            }
        }
        return matrix;
    }
}
