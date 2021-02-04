package test;

import resources.NearestNeighborV2;

public class ApplicationNearestNeighborTest
{
    public static void main(String[] args)
    {
        double[][] matrix1 = { {0, 15, 13, 4, 9, 11, 5, 6}, {15, 0, 5, 14, 7, 3, 20, 18}, {13, 5, 0, 10, 12, 4, 16, 14}, {4, 14, 10, 0, 13, 10, 2, 3}, {9, 7, 12, 13, 0, 6, 13, 13}, {11, 3, 4, 10 , 6, 0, 11, 9}, {5, 20, 16, 2, 13, 11, 0, 3}, {6, 18, 14, 3, 13, 9, 3, 0}};


        double[][] matrix2 = {  {0, 3, 16, 36, 4, 71, 6},
                                {3, 0, 83, 29, 25, 5, 82},
                                {16, 83, 0, 85, 57, 68, 16},
                                {36, 29, 85, 0, 83, 11, 34},
                                {4, 25, 57, 83, 0, 35, 100},
                                {71, 5, 68, 11, 35, 0, 4},
                                {6, 82, 16, 34, 100, 4, 0} };

        double[][] matrix3 = {  {0, 5.4, 3, 1.9, 16.99, 3.3333, 1.87},
                                {5.4, 0, 9.4, 72, 100, 40.323, 99.99},
                                {3, 9.4, 0, 27.487, 178, 3.5872, 482},
                                {1.9, 72, 27.487, 0, 1234, 42.4, 1},
                                {16.99, 100, 178, 1234, 0, 32, 69.32},
                                {3.3333, 40.323, 3.5872, 42.4, 32, 0, 1},
                                {1.87, 99.99, 482, 1, 69.32, 1, 0} };

        double[][] matrix4 = {  {0, 4, 7, 10, 5, 8, 12, 14},
                                {4, 0, 5, 9, 7, 10, 16, 17},
                                {7, 5, 0, 8, 10, 14, 19, 16},
                                {10, 9, 8, 0, 9, 19, 13, 8},
                                {5, 7, 10, 9, 0, 7, 5, 5},
                                {8, 10, 14, 19, 7, 0, 7, 9},
                                {12, 16, 19, 13, 5, 9, 0, 6},
                                {14, 17, 16, 8, 5, 9, 6, 0} };



        //MappingMatrixGenerator mappingMatrixGenerator = new MappingMatrixGenerator(10000);
        //double[][] matrix = mappingMatrixGenerator.generate();

        System.out.println("start");
        NearestNeighborV2 nearestNeighborV2 = new NearestNeighborV2();

        double shortestPath = Double.MAX_VALUE;
        double[][] finalMatrix = matrix4;

        for(int count = 0; count < matrix4.length; count++)
        {
            matrix4 = nearestNeighborV2.executeNearestNeighbor(matrix4, count);

            double path = matrix4[(int) matrix4[0][0]][(int) matrix4[matrix4.length - 1][matrix4.length - 1]];

            print(matrix4);

            for(int countMatrix = 0; countMatrix < matrix4.length - 1; countMatrix++)
                path += matrix4[(int) matrix4[countMatrix][countMatrix]][(int) matrix4[countMatrix + 1][countMatrix + 1]];

            if(path <= shortestPath)
            {
                shortestPath = path;
                finalMatrix = matrix4;
            }
        }
        print(finalMatrix);
        System.out.println(shortestPath);

        //NearestNeighborV2 nearestNeighborV2 = new NearestNeighborV2(matrix3.length);
        //nearestNeighborV2.executeNearestNeighbor(matrix3);
    }

    static private void print(double[][] matrix)
    {
        for(int count = 0; count < matrix.length; count++)
            System.out.print(matrix[count][count] + " ");
        System.out.println();
    }
}
