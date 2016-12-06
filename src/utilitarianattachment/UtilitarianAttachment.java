package utilitarianattachment;

import java.util.ArrayList;
import java.util.Random;

/*
 * @author Dan Giaime
 */
public class UtilitarianAttachment {

    public static void main(String[] args) {
        //Size of graph to make
        int size = 100;
        //List of all nodes in graph
        ArrayList<Node> currGraph = new ArrayList<>(size);
        //Random for calculating whether or not a connection occurs
        Random r = new Random();

        //For initial setup, we need a triangle of nodes, 
        //so we make, connect, and add these nodes.
        Node one = new Node();
        Node two = new Node();
        Node three = new Node();

        one.add(two);
        one.add(three);
        two.add(three);

        currGraph.add(one);
        currGraph.add(one);
        currGraph.add(one);

        //Loop (size) times to create the full graph
        for (int i = 0; i < size; i++) {
            //Create a new node to insert
            Node n = new Node();
            //See whether or not the new node will 
            //connect with each current node individually
            currGraph.add(n);
            for (int j = 0; j < currGraph.size(); j++) {
                System.out.println(i + " " + j);
                n.tryToAdd(currGraph.get(i), r);
            }
        }
        short[][] adjacencyMatrix = constructAdjacencyMatrix(currGraph);
        short[][] triangles = matMult(matMult(adjacencyMatrix, adjacencyMatrix), adjacencyMatrix);

        for (int i = 0; i < currGraph.size(); i++) {
            System.out.println("Degree of node " + i + " " + currGraph.get(i).getDegree());
        }
    }

    /*
     *  Constructs adjacency matrix from current graph.
     */
    public static short[][] constructAdjacencyMatrix(ArrayList<Node> currGraph) {
        short[][] adjacencyMatrix = new short[currGraph.size()][currGraph.size()];
        for (short i = 0; i < currGraph.size(); i++) {
            for (short j = 0; j < currGraph.size(); j++) {
                adjacencyMatrix[i][j] = (short) ((currGraph.get(i).hasNeighbor(currGraph.get(j))) ? 1 : 0);
            }
        }
        return adjacencyMatrix;
    }

    /*
     *  Returns average degree of nodes in current graph
     */
    public static double averageDegree(ArrayList<Node> currGraph) {
        return currGraph
                .stream()
                .map(n -> n.getDegree())
                .reduce(0, (a, b) -> a + b) / currGraph.size();
    }

    public static short[][] matMult(short[][] mat1, short[][] mat2) {
        short[][] outputMatrix = new short[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat1.length; k++) {
                    outputMatrix[i][k] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return outputMatrix;
    }
}
