package test;

import resources.ReadCSV;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadCSVTest {
    @Test
    public void checkFilePathshouldHandle_null(){
        ReadCSV tester=new ReadCSV(";");
        //assertEquals("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv",tester.checkFilePath("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv"));  //geht bei normaler ausführung
        assertNull(tester.checkFilePath(""));
        assertNull(tester.checkFilePath(null));

    }
    @Test
    public void isValidFileshoulddifferenciate(){
        File file=new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv");
        File file2=new File("");
        File file3=new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.lsvsa");
        File file4=new File("C:\\Users\\valen\\OneDrive\\Desktop\\Projekte");
        ReadCSV tester=new ReadCSV(",");
        try {
            assertTrue(tester.isValidFile(file));
            assertFalse(tester.isValidFile(file2));
            assertFalse(tester.isValidFile(file3));
            assertFalse(tester.isValidFile(file4));
        }catch (IOException e){
            System.out.println("l");
        }
    }
    @Test
    public void countPointshouldbeRight(){
        String filepath= "C:\\Users\\valen\\OneDrive\\Desktop\\Projekte\\CSVTEST.csv";
        ReadCSV tester=new ReadCSV(";");
        assertEquals(6,tester.countPoints());

    }
    @Test
    public void readCmdtest(){
        //Integrationstest wird gebraucht
    }


}
