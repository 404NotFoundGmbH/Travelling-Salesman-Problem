import org.junit.jupiter.api.Test;
import resources.NearestNeighborV2;
import resources.ReadCSV;
import test.NearestNeighborV2test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Integrationstest {
    String filepath="C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv";
    double[][] correcttable={{0.0,15.0,64.0,30.0,36.0,45.0,},
            {15.0,0.0,20.0,73.0,23.0,43.0,},
            {64.0,20.0,0.0,22.0,63.0,234.0,},
            {30.0,73.0,22.0,0.0,7.0,233.0,},
            {36.0,23.0,63.0,7.0,0.0,323.0,},
            {45.0,43.0,234.0,233.0,323.0,0.0,},};       //input the correct table

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
    double[][] correctmatrix4={{5.0,4.0,7.0,10.0,5.0,8.0,12.0,14.0,},
            {4.0,6.0,5.0,9.0,7.0,10.0,16.0,17.0,},
            {7.0,5.0,3.0,8.0,10.0,14.0,19.0,16.0,},
            {10.0,9.0,8.0,2.0,9.0,19.0,13.0,8.0,},
            {5.0,7.0,10.0,9.0,1.0,7.0,5.0,5.0,},
            {8.0,10.0,14.0,19.0,7.0,0.0,7.0,9.0,},
            {12.0,16.0,19.0,13.0,5.0,9.0,4.0,6.0,},
            {14.0,17.0,16.0,8.0,5.0,9.0,6.0,7.0,}};


    @Test
    public void integrationread(){
        ReadCSV test=new ReadCSV(";");
        test.filepath="C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv";
        test.readCmd();

        double[][] table = test.getdTable();

        int j=0,i=0;
        for (double[] a:table) {
            for (double ad:a){
                assertEquals(ad, correcttable[j][i]);
                i++;
            }
            i=0;
            j++;
        }
    }
    @Test
    public void integrationNNV2(){
        NearestNeighborV2test test=new NearestNeighborV2test();
        test.executeNearestNeighborTest();
    }

    @Test
    public void applicationtest(){
        integrationread();      //with filepath
        integrationNNV2();      //

    }

}
