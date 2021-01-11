package grahamAlgorithmus;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class GrahamAlgorithmus {
    public static void main(String[] args) {
        //Erstelle Point-Array
        Point[] wegpunkte = new Point[5];

        //Points Array mit Zufallswerten füllen
        for(int i = 0; i < wegpunkte.length; i++)
            wegpunkte[i] = new Point((int)(Math.random()*100),(int)(Math.random()*100));
        smallestY(wegpunkte);

    }

    /**
     * Diese Funktion ermittelt den Punkt mit der kleinsten Y-Koordinate.
     * @param wegpunkte Übergabeparamet ist das Point-Array wegpunkte.
     */
    private static void smallestY (Point[] wegpunkte){
        int lowest = wegpunkte[0].y;
        Point first = null;
        //Array nach y-Koordinate sortieren
        for (Point p: wegpunkte
        ) {
            if(p.y < lowest){
                lowest = p.y;
                first = p;
            }

        }
        System.out.println("Kleinste Y-Koordinate: "+lowest+"\nPunkt:"+first);
    }

    /*
    public static void readCSV(){
        //Pfad der csv-Datei angeben
        String csvFile = "";
        String line = "";
        //Trennzeichen angeben
        String csvSplitBy = ",";

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            while ((line = br.readLine()) != null){

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}


