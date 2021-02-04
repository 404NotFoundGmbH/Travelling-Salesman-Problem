package websocket;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import resources.*;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import resources.*;

/**
 * This class gets the coordinates from the website
 */
@WebSocket
public class EchoWebSocket {

    int algorithm = 0;  //for choosing between NearestNeighbor(2) and Convex Hull(1)


    /**
     * This converts a Point to Json format
     *
     * @param points List of points which should be converted
     * @return the point list as a String
     */
    public String pointToJson(List<Point2D> points) {
        String string = "[";

        for (Point2D i : points) {
            string = string.concat(i.getX() + "," + i.getY() + ",");
        }

        StringBuilder points1 = new StringBuilder(string);
        points1.setCharAt(string.length() - 1, ']');
        return String.valueOf(points1);
    }

    /**
     * This method converts the coordinates from format json to an array
     *
     * @param message these are the coordinates in json format
     * @return the matrix with the coordinates as double
     */
    public double[][] jsonToArray(String message) {
        algorithm = Integer.parseInt(String.valueOf(message.charAt(message.length() - 1)));
        System.out.println("Algorithm: " + algorithm);
        StringBuilder sb = new StringBuilder(message);
        sb.deleteCharAt(message.length() - 1);
        sb.deleteCharAt(message.length() - 2);
        sb.deleteCharAt(0);
        message = sb.toString();
        String[] str = message.split(",");

        double[] doubleValues = Arrays.stream(str)
                .mapToDouble(Double::parseDouble)
                .toArray();

        double[][] points = new double[(doubleValues.length / 2)][2];
        List<Double> longi = new ArrayList<>();
        List<Double> lat = new ArrayList<>();

        for (int i = 0; i < doubleValues.length; i++) {
            if (i % 2 == 0) {
                longi.add(doubleValues[i]);
                lat.add(doubleValues[i + 1]);
            }
        }

        for (int i = 0; i < longi.size(); i++) {
            points[i][0] = longi.get(i).doubleValue();
            points[i][1] = lat.get(i).doubleValue();
        }

        return points;
    }

    public String array2dToJson(double[][] array) {
        String str = "[";

        for (int i = 0; i < array.length; i++) {
            str = str.concat(String.valueOf(array[i][0]));
            str = str.concat(",");
            str = str.concat(String.valueOf(array[i][1]));
            str = str.concat(",");
        }

        StringBuilder str1 = new StringBuilder(str);
        str1.setCharAt(str.length() - 1, ']');
        return String.valueOf(str1);
    }

    // Store sessions if you want to, for example, broadcast a message to all users
    /**
     * Store session if you want to, e.g. broadcast a message to all users
     */
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    /**
     * This method gets the connection to a session
     *
     * @param session session which you want to connect to
     */
    @OnWebSocketConnect
    public void connected(Session session) {
        sessions.add(session);
    }

    /**
     * This method closes the connection to a session
     *
     * @param session    session which you want to close
     * @param statusCode if 0 everything is ok, if 1 something went wrong
     * @param reason     if something went wrong this shows the reason why
     */
    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }

    /**
     * This method prints a message
     *
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
        double[][] array = jsonToArray(message);
        System.out.println("Converted to array" + Arrays.deepToString(array));

        switch (algorithm) {
            case 1:
                List<Point2D> points = new ArrayList<>();

                for (int i = 0; i < array.length; i++) {
                    points.add(i, new Point2D.Double(array[i][0], array[i][1]));
                }
                System.out.println(points);

                GrahamAlgorithmusV2.setNodes(points);
                points = GrahamAlgorithmusV2.computeConvexHull();
                String str = pointToJson(points);
                session.getRemote().sendString(str);
                break;
            case 2:
                System.out.println(Arrays.deepToString(array));
                double[][] table1 = CoordinatesConvert.convert(array);
                array = NearestNeighborCoords.cordinatesNearestNeighbor(array);

                int e=0;
                double[][] array1 = new double[array.length+1][2]; 
                for (int i=0; i<array.length;i++){
                    array1[i][0] = array[i][0];
                    array1[i][1] = array[i][1];
                    e++;
                }
                
                array1[e][0] = array[0][0];
                array1[e][1] = array[0][1];


                String str1 = array2dToJson(array1);
                session.getRemote().sendString(str1);
                break;
            case 3:
                System.out.println(Arrays.deepToString(array));
                double[][] table2 = CoordinatesConvert.convert(array);
                array = NearestNeighborCoordsExtended.CordinatesNearestNeighborExtended(table2);

                int f=0;
                double[][] array2 = new double[array.length+1][2];
                for (int i=0; i<array.length;i++){
                    array2[i][0] = array[i][0];
                    array2[i][1] = array[i][1];
                    f++;
                }

                array2[f][0] = array[0][0];
                array2[f][1] = array[0][1];

                String str2 = array2dToJson(array2);
                session.getRemote().sendString(str2);
                break;
            default:
                System.out.println("Something went wrong");
                break;

        }
    }
}