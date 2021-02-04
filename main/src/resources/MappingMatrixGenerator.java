package resources;

/**
 * This class calculates coordinates from an adjacency matrix
 */
public class MappingMatrixGenerator
{
    private double[][] matrix;

    /**
     * This constructor is used to get the adjacency matrix
     * @param points the adjacency matrix
     */
    public MappingMatrixGenerator(int points)
    {
        this.matrix = new double[points][points];
    }

    /**
     * This methode generates the matrix with coordinates
     * @return the matrix with coordinates
     */
    public double[][] generate()
    {
        for(int x = 0; x < this.matrix.length; x++)
        {
            for(int y = this.matrix.length - 1; y >= x; y--)
            {
                if(y != x)
                {
                    this.matrix[x][y] = (Math.random()* 100);
                    this.matrix[y][x] = this.matrix[x][y];
                }
                else
                    this.matrix[x][y] = 0;
            }
        }
        return this.matrix;
    }
}
