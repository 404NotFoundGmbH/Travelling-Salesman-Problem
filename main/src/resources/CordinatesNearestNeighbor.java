public class NearestNeighborCoords
{
    public double[][] CordinatesNearestNeighbor(double[][] coords)
    {
        double shortestDistance;
        int safe = 0;

        for(int countRow = 1; countRow < coords.length; countRow++)
        {
            shortestDistance = Double.MAX_VALUE;
            for(int countColumn = coords.length - 1; countColumn > countRow - 1; countColumn--)
            {
                if(shortestDistance > Math.sqrt(Math.pow(coords[countColumn][0] - coords[countRow - 1][0], 2) + Math.pow(coords[countColumn][1] - coords[countRow - 1][1], 2)))
                {
                    shortestDistance = Math.sqrt(Math.pow(coords[countColumn][0] - coords[countRow - 1][0], 2) + Math.pow(coords[countColumn][1] - coords[countRow - 1][1], 2));
                    safe = countColumn;
                }
            }
            coords[safe][0] = swap(coords[countRow][0], coords[countRow][0] = coords[safe][0]);
            coords[safe][1] = swap(coords[countRow][1], coords[countRow][1] = coords[safe][1]);
        }
        return coords;
    }

    private double swap(double pos2, double pos1) { return pos2; }
    
    public double getDistance(double[][] coords)
    {
        double length = Math.sqrt(Math.pow(coords[0][0] - coords[coords.length - 1][0], 2) + Math.pow(coords[0][1] - coords[coords.length - 1][1], 2));

        for(int count = 1; count < coords.length; count++)
            length += Math.sqrt(Math.pow(coords[count][0] - coords[count - 1][0], 2) + Math.pow(coords[count][1] - coords[count - 1][1], 2));

        return length;
    }
}
