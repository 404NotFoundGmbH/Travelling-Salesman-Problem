package websocket;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import resources.CoordinatesConvert;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class gets the coordinates from the website
 */
@WebSocket
public class EchoWebSocket {
    /**
     * This converts a Point to Json format
     * @param points List of points which should be converted
     * @return the point list as a String
     */
    public String pointToJson(List<Point2D> points){
        String string = "[";

        for (Point2D i : points){
            string = string.concat(i.getX() + "," + i.getY() + ",");
        }

        StringBuilder points1 = new StringBuilder(string);
        points1.setCharAt(string.length()-1, ']');
        return String.valueOf(points1);
    }

    /**
     * This method converts the coordinates from format json to an array
     * @param message these are the coordinates in json format
     * @return the matrix with the coordinates as double
     */
    public double[][] jsonToArray(String message) {
        StringBuilder sb = new StringBuilder(message);
        sb.deleteCharAt(message.length() - 1);
        sb.deleteCharAt(0);
        message = sb.toString();
        String[] str = message.split(",");

        double[] doubleValues = Arrays.stream(str)
                .mapToDouble(Double::parseDouble)
                .toArray();

        //List<Point> points = new ArrayList<>();
        double[][] points = new double[(doubleValues.length/2)][2];
        List<Double> longi = new ArrayList<>();
        List<Double> lat = new ArrayList<>();
        //double[] longi;
        //double[] lat;

        for (int i=0;i<doubleValues.length;i++) {
            if (i%2 == 0){
                longi.add(doubleValues[i]);
                lat.add(doubleValues[i+1]);
            }
        }

        for (int i=0;i<longi.size();i++){
            points[i][0] = longi.get(i).doubleValue();
            points[i][1] = lat.get(i).doubleValue();
        }

        return points;
    }


    // Store sessions if you want to, for example, broadcast a message to all users
    /**
     * Store session if you want to, e.g. broadcast a message to all users
     */
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    /**
     * This method gets the connection to a session
     * @param session session which you want to connect to
     */
    @OnWebSocketConnect
    public void connected(Session session) {
        sessions.add(session);
    }

    /**
     * This method closes the connection to a session
     * @param session session which you want to close
     * @param statusCode if 0 everything is ok, if 1 something went wrong
     * @param reason if something went wrong this shows the reason why
     */
    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }

    /**
     * This method prints a message
     * @param session associated session
     * @param message message which should be shown
     * @throws IOException Exception is thrown if something went wrong
     */
    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Got: " + message);   // Print message
        double[][] test = jsonToArray(message);
        System.out.println(Arrays.deepToString(test));
        test = CoordinatesConvert.convert(test);
        System.out.println(Arrays.deepToString(test));

        /*
        GrahamAlgorithmusV2.allNodes.add(new Point(32,27));
        GrahamAlgorithmusV2.allNodes.add(new Point(20,11));
        GrahamAlgorithmusV2.allNodes.add(new Point(50,43));
        GrahamAlgorithmusV2.allNodes.add(new Point(24,53));
        */

            /*
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(GrahamAlgorithmusV2.computeConvexHull());
             */


        session.getRemote().sendString(Arrays.deepToString(test)); // sends the into json format converted points
        //session.getRemote().sendString(String.valueOf(GrahamAlgorithmusV2.finalDistance));  //sends the distance of convex Hull
    }

}