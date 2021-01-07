package it;

// Adjacency Matrix representation in Java

public class Matrix {
    private boolean adjazenzMatrix[][];
    private int numPunkte;

    // Initialize the matrix
    public Matrix(int numPunkte) {
        this.numPunkte = numPunkte;
        adjazenzMatrix = new boolean[numPunkte][numPunkte];
    }

    // Add edges
    public void addEdge(int i, int j) {
        adjazenzMatrix[i][j] = true;
        adjazenzMatrix[j][i] = true;
    }

    // Remove edges
    public void removeEdge(int i, int j) {
        adjazenzMatrix[i][j] = false;
        adjazenzMatrix[j][i] = false;
    }

    //Matrix printen
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numPunkte; i++) {
            s.append(i + ": ");
            for (boolean j : adjazenzMatrix[i]) {
                s.append((j ? 1 : 0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
