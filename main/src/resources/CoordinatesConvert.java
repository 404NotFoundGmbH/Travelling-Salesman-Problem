public class CoordinatesConvert
{
    public static void main(String[] args)
    {
        double[][] coords = {{5,6,7,4,3,5,6,4},
                             {5,6,2,5,7,45,3,4}};
        double[][] test = convert(coords);

        for(int count = 0; count < test.length; count++)
        {
            for(int count1 = 0; count1 < test.length; count1++)
                System.out.print(test[count][count1] + " ");
            System.out.println();
        }
    }

    private static double[][] convert(double[][] coords)
    {
        double[][] matrix = new double[coords[0].length][coords[0].length];

        for(int countRow = 0; countRow < matrix.length; countRow++)
        {
            for(int countColumn = countRow; countColumn < matrix.length; countColumn++)
            {
                if(countColumn != countRow)
                {
                    matrix[countRow][countColumn] = Math.sqrt(Math.pow(coords[0][countRow] - coords[0][countColumn], 2) + Math.pow(coords[1][countRow] - coords[1][countColumn], 2));
                    matrix[countColumn][countRow] = matrix[countRow][countColumn];
                }
                else
                    matrix[countRow][countColumn] = 0;
            }
        }
        return matrix;
    }
}