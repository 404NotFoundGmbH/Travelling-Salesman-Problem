package resources;

/**
 * 
 */
public class NearestNeighbor
{
    /**
     * Is needed so that only numbers that can still be compared are
     * compared and you can see the Path. Points that have already
     * been connected can no longer be compared.
     */
    private int[] pointsArray;

    /**
     * Values are added to the pointsArray. These values are simply
     * numbers from 1 to root of the size of the mapping matrix.
     *
     * @param size  to initialize a pointsArray size
     */
    public NearestNeighbor(int size)
    {
        this.pointsArray = new int[size];

        for (int count = 0; count < size; count++)
            this.pointsArray[count] = count;
    }

    /**
     * In the pointsArray, the point that is currently being calculated
     * is swapped with the point that is in the pointsArray at the position: length
     * of the pointsArray - number of points already visited. Then the remaining points
     * are compared that are located before the position of the current point
     * (all those that are smaller than: Length of pointsArray - number of points
     * already visited) and the one with the shortest distance is selected. This
     * is repeated until one point is left. Then the points are in the pointsArray in
     * the order in which they should be travelled, but backwards.
     *
     * @param matrix        this is the mapping matrix
     * @param startPoint    you can set your start point
     */
    public void searchNearestNeighbor(int[][] matrix, int startPoint)
    {
        int shortestWay;

        for(int countRow = 1; countRow < this.pointsArray.length; countRow++)
        {
            this.pointsArray[startPoint] = swap(this.pointsArray[this.pointsArray.length - countRow], this.pointsArray[this.pointsArray.length - countRow] = this.pointsArray[startPoint]);

            shortestWay = matrix[this.pointsArray[this.pointsArray.length - countRow]][this.pointsArray[0]];
            startPoint = 0;

            for(int countColumn = 1; countColumn < this.pointsArray.length - countRow; countColumn++)
            {
                if(shortestWay > matrix[this.pointsArray[this.pointsArray.length - countRow]][this.pointsArray[countColumn]])
                {
                    shortestWay = matrix[this.pointsArray[this.pointsArray.length - countRow]][this.pointsArray[countColumn]];
                    startPoint = countColumn;
                }
            }
        }
        print();    //to see if it works
    }


    /**
     * Number pos2 is passed and returned to pos1, then pos1 defined as pos1 = pos2.
     *
     * @param pos2  Second position
     * @param pos1  First position
     * @return      First position
     */
    private int swap(int pos2, int pos1)
    {
        return pos2;
    }

    /**
     * Prints the pointsArray backwards to see the Path.
     */
    private void print()
    {
        for (int count = this.pointsArray.length - 1; count >= 0; count--)
            System.out.print(this.pointsArray[count] + " ");
    }
}