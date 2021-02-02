package resources;

import org.junit.Test;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class GrahamAlgorithmusV2Test {

    @Test
    public void getLowestYTest(){
        ArrayList<Point2D> points = new ArrayList<>();

        Point2D a = new Point2D.Double(1.43, 0.74);
        Point2D b = new Point2D.Double(5.71, 1.34);
        Point2D c = new Point2D.Double(4.79, 2.86);
        Point2D d = new Point2D.Double(4,5);
        Point2D e = new Point2D.Double(1.89,4.66);
        Point2D f = new Point2D.Double(1.49,3.46);
        Point2D g = new Point2D.Double(0.65,3.28);
        Point2D h = new Point2D.Double(0.09,2.38);
        Point2D i = new Point2D.Double(0.99,0.46);

        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        points.add(e);
        points.add(f);
        points.add(g);
        points.add(h);
        points.add(i);

        GrahamAlgorithmusV2.setNodes(points);

    }
    @Test
    public void sortAllNodesByAngleAndSolutionTest(){

        ArrayList<Point2D> points = new ArrayList<>();

        Point2D a = new Point2D.Double(3.43, 0.74);
        Point2D b = new Point2D.Double(5.71, 1.34);
        Point2D c = new Point2D.Double(4.79, 2.86);
        Point2D d = new Point2D.Double(4,5);
        Point2D e = new Point2D.Double(1.89,4.66);
        Point2D f = new Point2D.Double(1.49,3.46);
        Point2D g = new Point2D.Double(0.65,3.28);
        Point2D h = new Point2D.Double(0.09,2.38);
        Point2D i = new Point2D.Double(0.99,0.46);

        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        points.add(e);
        points.add(f);
        points.add(g);
        points.add(h);
        points.add(i);

        GrahamAlgorithmusV2.setNodes(points);
        ArrayList<Point2D> sortedPoints = new ArrayList<>( GrahamAlgorithmusV2.sortAllNodesByAngle());
        ArrayList<Point2D> allPointsAdded = new ArrayList<>(GrahamAlgorithmusV2.computeConvexHull());

        assertThat(sortedPoints.get(0), is(i));
        assertThat(sortedPoints.get(1), is(a));
        assertThat(sortedPoints.get(2), is(b));
        assertThat(sortedPoints.get(3), is(c));
        assertThat(sortedPoints.get(4), is(d));
        assertThat(sortedPoints.get(5), is(e));
        assertThat(sortedPoints.get(6), is(f));
        assertThat(sortedPoints.get(7), is(g));
        assertThat(sortedPoints.get(8), is(h));

        assertThat(allPointsAdded.get(0), is(i));
        assertThat(allPointsAdded.get(1), is(a));
        assertThat(allPointsAdded.get(2), is(b));
        assertThat(allPointsAdded.get(3), is(c));
        assertThat(allPointsAdded.get(4), is(d));
        assertThat(allPointsAdded.get(5), is(e));
        assertThat(allPointsAdded.get(6), is(f));
        assertThat(allPointsAdded.get(7), is(g));
        assertThat(allPointsAdded.get(8), is(h));
        assertThat(allPointsAdded.get(9), is(i));
    }

    public static void main(String[] args) {
        GrahamAlgorithmusV2Test g = new GrahamAlgorithmusV2Test();
        g.sortAllNodesByAngleAndSolutionTest();
    }

}
