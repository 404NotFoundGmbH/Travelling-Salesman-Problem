package tests;

import resources.ReadCSV;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadCSVTest {
    @Test
    public void setFilePathshouldreadPath(){
        ReadCSV tester=new ReadCSV(";");

        assertEquals(String,tester.setFilepath(););
        tester.setFilepath();
    }

}
