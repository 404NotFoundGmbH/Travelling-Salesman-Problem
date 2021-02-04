package test;

import org.junit.jupiter.api.Test;
import resources.ReadCSV;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the correct reading of the csv file
 */
public class ReadCSVTest {
    /**
     * tests if the program is working properly if the file path is null
     */
    @Test
    public void checkFilePathshouldHandle_null(){
        ReadCSV tester=new ReadCSV(";");
        //assertEquals("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv",tester.checkFilePath("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv"));  //geht bei normaler ausführung
        assertNull(tester.checkFilePath(""));
        assertNull(tester.checkFilePath(null));

    }

    /**
     * tests if it shows correctly if a file is not valid
     */
    @Test
    public void isValidFileshoulddifferenciate(){
        File file=new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv");
        File file2=new File("");
        File file3=new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.lsvsa");
        File file4=new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte");
        ReadCSV tester=new ReadCSV(",");

            assertTrue(tester.isValidFile(file));
            assertFalse(tester.isValidFile(file2));
            assertFalse(tester.isValidFile(file3));
            assertFalse(tester.isValidFile(file4));

    }

    /**
     * tests if the number of points read is right
     */
    @Test
    public void countPointshouldbeRight(){
        String filepath= "C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv";
        ReadCSV tester=new ReadCSV(";");
        assertEquals(6,tester.countPoints());

    }

    /**
     * tests the input from the commandline
     */
    @Test
    public void readCmdtest(){
        //Integrationstest wird gebraucht
    }


}
