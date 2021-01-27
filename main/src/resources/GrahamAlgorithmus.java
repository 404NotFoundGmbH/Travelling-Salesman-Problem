package resources;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class GrahamAlgorithmus {
    private static List<Point> rechts = new ArrayList<>();
    private static List<Point> links = new ArrayList<>();
    private static Point[] rechtsArray;
    private static Point[] linksArray;
    public static void main(String[] args) {
        //Erstelle Point-Array
        Point[] wegpunkte = new Point[9];

        /*Points Array mit Zufallswerten füllen
        for(int i = 0; i < wegpunkte.length-2; i++)
            wegpunkte[i] = new Point((int)(Math.random()*100),(int)(Math.random()*100));
        */
        wegpunkte[0] = new Point(59, 38);
        wegpunkte[1] = new Point(32,27);
        wegpunkte[2] = new Point(87,20);
        wegpunkte[3] = new Point(100,5);
        wegpunkte[4] = new Point(10,5);
        wegpunkte[5] = new Point(70, 9);
        wegpunkte[6] = new Point(69,24);
        wegpunkte[7] = new Point(46,14);
        wegpunkte[8] = new Point(66,36);
        //System.out.println(Arrays.toString(wegpunkte));
        //Array wird nach aufsteigendem y-Wert sortiert
        Arrays.sort(wegpunkte, GrahamAlgorithmus::comparingY);
        //System.out.println(Arrays.toString(wegpunkte));

        Point a = wegpunkte[0];
        Point b = wegpunkte[wegpunkte.length-1];

        Arrays.sort(wegpunkte, GrahamAlgorithmus::comparingX);
        //List<Point> rechts = new ArrayList<Point>();
        //List<Point> links = new ArrayList<Point>();

        //Prüfen, ob sich ein Punkt im Uhrzeigersinn zu Punkt0 befindet
        for(int i = 0; i < wegpunkte.length; i++){
            if(wegpunkte[i] == a){
                rechts.add(a);
                links.add(a);
            }
            else if(wegpunkte[i] ==b){
                rechts.add(b);
                links.add(b);
            }
            //cw
            else if((b.x-a.x)*(wegpunkte[i].y-b.y)-(wegpunkte[i].x-b.x)*(b.y-a.y) < 0)
                rechts.add(wegpunkte[i]);
            else
                links.add(wegpunkte[i]);
        }
        //System.out.println(rechts);
        //System.out.println(links);

        //Convert List to Array
        //rechtsArray = rechts.toArray(new Point[rechts.size()]);
        //linksArray = links.toArray(new Point[links.size()]);

        //sort x-Koordinate
        rechts.sort(GrahamAlgorithmus::comparingX);
        links.sort(GrahamAlgorithmus::comparingX);
        //System.out.println(Arrays.toString(rechtsArray));
        //System.out.println(Arrays.toString(linksArray));

        List<Point> upperCon;
        List<Point> lowerCon;

        upperCon = computeHull(rechts, b);
        lowerCon = computeHull(links, a);

        upperCon.add(a);
        upperCon.add(0,a);
        lowerCon.add(b);
        //Put only the remaining points in the List
        rechts.removeAll(upperCon);
        links.removeAll(lowerCon);

        //Doppeltes Element entfernen
        //upperCon.remove(upperCon.size()-1);

        //Upper und Lower convex zusammenführen
        //List<Point> convexHull = Stream.concat(upperCon.stream(), lowerCon.stream()).collect(Collectors.toList());

        //System.out.println(convexHull);

        System.out.println("Remaining Points1"+rechts);
        System.out.println("Remaining Points2"+links);

        System.out.println(upperCon);

        System.out.println("RECHTS: "+rechts);
        getShortestDist(rechts, upperCon);
    }
    /**
     * Diese Funktion vergleicht zwei Points
     * nach ihrer y-Koordinate
     * @param p1 Point 1
     * @param p2 Point 2
     * @return compare-Wert der kleinsten y-Koordinate
     */
    private static int comparingY(Point p1, Point p2){
        int yComp = Integer.compare(p1.y, p2.y);
        if(yComp == 0)
            return Integer.compare(p1.x, p2.x);
        else
            return yComp;
    }

    /**
     * Diese Funktion vergleicht zwei Points
     * nach ihrer x-Koordinate
     * @param p1 Point 1
     * @param p2 Point 2
     * @return compare-Wert der kleinsten x-Koordinate
     */
    public static int comparingX(Point p1, Point p2){
        int xComp = Integer.compare(p1.x, p2.x);
        if(xComp == 0)
            return Integer.compare(p1.y, p2.y);
        else
            return xComp;
    }

    /**
     * Diese Methode berechnet die convex hull der oberen und der unteren Sets
     * @param nodes Point Array
     * @param last letzter Punkt im Array
     * @return computed convex Hull
     */
    public static List<Point> computeHull(List<Point> nodes, Point last){
        List<Point> conHull = new ArrayList<>();
        conHull.add(nodes.get(nodes.size() - 1));
        conHull.add(nodes.get(nodes.size() - 2));
        int back = 0;

        for(int i = nodes.size()-1; nodes.get(i - 1) != last; i--){
            Point a = nodes.get(i + back);
            Point b = nodes.get(i - 1);
            Point c = nodes.get(i - 2);
            //clockwise -> most recent entfernen
            if((b.x-a.x)*(c.y-b.y)-(c.x-b.x)*(b.y-a.y) < 0){
                conHull.remove(conHull.size()-1);
                back = 1;
            }
            else {
                back = 0;
            }
            conHull.add(c);
        }
        System.out.println(conHull);
        return conHull;
    }

    public static void getShortestDist(List<Point> remainingNodes, List<Point> hullNodes){
        List<Point> allPoints = new ArrayList<>(hullNodes);


        for(int i = 0; i < remainingNodes.size(); i++){
            int bestIndex = 0;
            double shortestDist = Integer.MAX_VALUE;
            double currDist = 0;

            for(int j = 0; j < hullNodes.size()-1; j++){
                currDist=(Math.sqrt(Math.pow(hullNodes.get(j).x-remainingNodes.get(i).x,2)+Math.pow(hullNodes.get(j).y-remainingNodes.get(i).y,2))+(Math.sqrt(Math.pow(remainingNodes.get(i).x-hullNodes.get(j+1).x,2)+Math.pow(remainingNodes.get(i).y-hullNodes.get(j+1).y,2)))-(Math.sqrt(Math.pow(hullNodes.get(j).x-hullNodes.get(j+1).x,2)+Math.pow(hullNodes.get(j).y-hullNodes.get(j+1).y,2))));
                System.out.println(currDist);
                //currDist /= (Math.sqrt(Math.pow(hullNodes.get(j).x-hullNodes.get(j+1).x,2))+Math.pow(hullNodes.get(j).y-hullNodes.get(j+1).y,2));
                //System.out.println(currDist);
                if(currDist < shortestDist){
                    shortestDist = currDist;
                    bestIndex = j;
                    //System.out.println("Best Index ="+bestIndex+ remainingNodes.get(i));
                }
            }
            hullNodes.add(bestIndex+1, remainingNodes.get(i));
            System.out.println("DIST="+ shortestDist + " "+ hullNodes.get(bestIndex));
        }
        System.out.println(hullNodes);

    }
}


