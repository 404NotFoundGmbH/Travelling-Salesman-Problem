package grahamalgorithmus;

import java.awt.Point;
import java.util.*;


/**
 * This enum is used to save the direction between two points
 */
enum Direction {
    CLOCKWISE, COUNTERCLOCKWISE, COLLINEAR;
}

/**
 * This class makes the convex hull out of a set of points, using the Graham Scan
 * and inserts the remaining nodes in order to solve the TSP (Traveling Salesman Problem)
 */
public class GrahamAlgorithmusV2 {
   private static List<Point> allNodes = new ArrayList<>();
   private static List<Point> sortedPoints;
    /**
     * This function saves the given points into the list <code>allNodes</code>
     * @param points List of points which should be calculated
     */
   public static void setNodes(List<Point> points){
       /* This function saves all points from the List into the List allNodes
       allNodes = points;
        */

       allNodes.add(0, new Point(2, 4));
       allNodes.add(1, new Point(3, 5));
       allNodes.add(2, new Point(3, 3));
       allNodes.add(3, new Point(4, 6));
       allNodes.add(4, new Point(4, 4));
       allNodes.add(5, new Point(4, 2));
       allNodes.add(6, new Point(5, 5));
       allNodes.add(7, new Point(5, 3));
       allNodes.add(8, new Point(6, 4));

   }

    /**
     * This function determines the convex hull of the given points.
     * @return the computed convex hull of <code>allNodes</code>
     */
   public static List <Point> computeConvexHull(){

       if(allNodes.size() < 3){
           allNodes.sort(GrahamAlgorithmusV2::getLowestY);
           allNodes.add(allNodes.get(1));
           return allNodes;
       }
       sortedPoints = new ArrayList<>(sortAllNodesByAngle());

       Stack<Point> pointStack = new Stack<>();
       //Add the first two points of the sorted array to the convex-hull-stack
       pointStack.push(sortedPoints.get(0));
       pointStack.push(sortedPoints.get(1));

       for(int i = 2; i < sortedPoints.size(); i++){

           //Point a = current point
           Point a = sortedPoints.get(i);
           //Point b = last point from the stack by removing it from the stack
           Point b = pointStack.pop();
           //Point c = last point from the stack(after removing the very last) by NOT removing it from the stack
           Point c = pointStack.peek();

           //Determine if points are CLOCKWISE, COUNTERCLOCKWISE or COLLINEAR
           Direction direction = determineDirection(c, b, a);
           //point b is part of the convex hull and so is point a
           if(direction == Direction.COUNTERCLOCKWISE){
               pointStack.push(b);
               pointStack.push(a);
           }
           //point b is not part of the convex hull -> start point remains the same and compares with the next point
           else if(direction == Direction.CLOCKWISE)
               i--;
           //point b is not part of the convex hull, but point a is
           else
               pointStack.push(a);
       }
       //Close stack with so that the start point is also the end point
       pointStack.push(sortedPoints.get(0));
       //give the point stack as an ArrayList to the function insertNodes and return the completed list with all points
       return insertNodes(new ArrayList<Point>(pointStack));
   }

    /**
     * This function inserts the remaining nodes which are not part of the convex hull
     * @param convexHull the convex hull which was computed by the function @see GrahamAlgorithmusV2#computeConvexHull
     * @return the completed list with all nodes
     */
   public static List<Point> insertNodes(List<Point> convexHull){
       //Puts all remaining nodes into the list remaining nodes by copying the List sortedPoints and removing the convex hull
       List<Point> remainingNodes = new ArrayList<>(sortedPoints);
       remainingNodes.removeAll(convexHull);

       double finalDistance = 0;

       for(int i = 0; i < remainingNodes.size(); i++){
           int firstIndex = 0;
           double shortestDist = Integer.MAX_VALUE;
           double currDist = 0;

           for(int j = 0; j < convexHull.size()-1; j++){
               //Calculation: distOf(hullPoint1 -> remainingNode1) + distOf(remainingNode1 -> hullPoint2) - distOf(hullPoint1 -> hullPoint2)
               currDist=(Math.sqrt(Math.pow(convexHull.get(j).x-remainingNodes.get(i).x,2)+Math.pow(convexHull.get(j).y-remainingNodes.get(i).y,2))+(Math.sqrt(Math.pow(remainingNodes.get(i).x-convexHull.get(j+1).x,2)+Math.pow(remainingNodes.get(i).y-convexHull.get(j+1).y,2)))-(Math.sqrt(Math.pow(convexHull.get(j).x-convexHull.get(j+1).x,2)+Math.pow(convexHull.get(j).y-convexHull.get(j+1).y,2))));
               if(currDist < shortestDist){
                   shortestDist = currDist;
                   firstIndex = j;
               }
           }
           convexHull.add(firstIndex+1, remainingNodes.get(i));
       }

       for(int i = 0; i < convexHull.size()-1; i++){
           Point lowest = convexHull.get(i);
           Point p1 = convexHull.get(i+1);
           double distance = Math.sqrt((((long)lowest.x - p1.x) * ((long)lowest.x - p1.x)) +
                   (((long)lowest.y - p1.y) * ((long)lowest.y - p1.y)));
           finalDistance+=distance;
       }
       System.out.println(finalDistance);
       return convexHull;
   }


    /**
     * This function is used to determine the point with the lowest y-coordinate
     * @param p1 First point to compare with
     * @param p2 Second point to compare with
     * @return -1 if(p1.y < p2.y), 1 if(p1.y > ps.y)
     */
   public static int getLowestY(Point p1, Point p2){
       //Compare y coordinate
       int yComp = Integer.compare(p1.y, p2.y);
       //If both y are the same compare the both x-coordinates
       if(yComp == 0)
           return Integer.compare(p1.x, p2.x);
       else
           return yComp;
   }

    /**
     * This function sorts all nodes by their angle. It determines the angle they and
     * the lowest point make with the x-axis. If some points have the same angle they
     * get sorted by their ascending distance to the lowest point.
     * @return a sorted set of points
     */

   public static Set<Point> sortAllNodesByAngle(){
       //Sort the list allNodes by their Y-coordinate to get the lowest point
        allNodes.sort(GrahamAlgorithmusV2::getLowestY);
        final Point lowest = allNodes.get(0);

        //Makes a new set of points sorted by their angle
        Set<Point> sortedPoints = new TreeSet<Point>((p1, p2) -> {

            if(p1 == p2 || p1.equals(p2))
                return 0;

            //This calculates the angle of 2 points. atan2() can handle y=0 or x=0
            //We use long for the coordinates so there's no risk for int-underflow
            double angleP1 = Math.atan2((long)p1.y - lowest.y, (long)p1.x - lowest.x);
            double angleP2 = Math.atan2((long)p2.y - lowest.y, (long)p2.x - lowest.x);

            //If both angles are not the same this returns the comparing-value
            if(angleP1 != angleP2)
                return Double.compare(angleP1, angleP2);

            //If the points are collinear this determines the point which is nearer to the lowest point
            else{
                double distanceP1 = Math.sqrt((((long)lowest.x - p1.x) * ((long)lowest.x - p1.x)) +
                        (((long)lowest.y - p1.y) * ((long)lowest.y - p1.y)));
                double distanceP2 = Math.sqrt((((long)lowest.x - p2.x) * ((long)lowest.x - p2.x)) +
                        (((long)lowest.y - p2.y) * ((long)lowest.y - p2.y)));

                return Double.compare(distanceP1, distanceP2);
            }
            });

        sortedPoints.addAll(allNodes);
        return sortedPoints;
   }

    /**
     * This function determines the direction/turn from one point to another and returns the appropriate enum.
     * In order to determine the turn we use the cross product of three points.
     * @param p1 the start-point
     * @param p2 the middle-point
     * @param p3 the end-point
     * @return the appropriate enum (defines the direction/turn)
     */
   public static Direction determineDirection(Point p1, Point p2, Point p3){

       //This calculates the crossproduct of three points (2 vectors)
       long crossProduct = (((long)p2.x - p1.x) * ((long)p3.y - p1.y)) - (((long)p2.y - p1.y)*((long)p3.x - p1.x));

       if(crossProduct > 0)
           return Direction.COUNTERCLOCKWISE;
       else if(crossProduct < 0)
           return Direction.CLOCKWISE;
       else
           return Direction.COLLINEAR;
   }

    public static void main(String[] args) {
        allNodes.add( new Point(59, 38));
        allNodes.add(new Point(32,27));
        allNodes.add(new Point(87,20));
        allNodes.add(new Point(100,5));
        allNodes.add(new Point(10,5));
        allNodes.add(new Point(70, 9));
        allNodes.add(new Point(69,24));
        allNodes.add(new Point(46,14));
        allNodes.add(new Point(66,36));

        System.out.println(computeConvexHull());


    }
}

