package utilitarianattachment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;

/*
 * @author Dan Giaime
 */
public class UtilitarianAttachment {

    public static void main(String[] args) throws IOException {
        //Size of graph to make
        long startTime = System.nanoTime();
        String input = "";
        char[] inputSize = new char[6];
        System.console().reader().read(inputSize);
        for (int i = 0; i < 6; i++) {
            if(inputSize[i]!=' '){input+=inputSize[i];}
        }
        int size = Integer.parseInt(input.trim());
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
        currGraph.add(two);
        currGraph.add(three);
        
        //String to save for the sake of it being printed 50000 times
        String output = "Computing node ";
        //Loop (size) times to create the full graph
        for (int i = 3; i < size; i++) {
            //Create a new node to insert
            Node n = new Node();
            //See whether or not the new node will 
            //connect with each current node individually
            currGraph.add(n);
            System.console().writer().println(output+i);
            for (int j = 0; j < currGraph.size(); j++) {
                currGraph.get(currGraph.size() - 1).tryToAdd(currGraph.get(j), r);
            }
        }
        //short[][] adjacencyMatrix = constructAdjacencyMatrix(currGraph);
        //short[][] triangles = matMult(
        //        matMult(adjacencyMatrix, adjacencyMatrix), adjacencyMatrix);

        HashMap<Integer, Integer> degreeFreq = new HashMap<>();
        for (Node n : currGraph) {
            if (!degreeFreq.containsKey(n.getDegree())) {
                degreeFreq.put(n.getDegree(), 1);
            } else {
                degreeFreq.replace(n.getDegree(), degreeFreq.get(n.getDegree()) + 1);
            }
        }
        System.console().writer().println("----------");
        System.console().writer().println("Frequency to copy");
        degreeFreq.forEach((k, v) -> {
            System.console().writer().println(v);
        });
        System.console().writer().println("----------");
        System.console().writer().println("Degree to copy");
        degreeFreq.forEach((k, v) -> {
            System.console().writer().println(k);
        });
        System.console().writer().println("----------");
        System.console().writer().println("Summation of data: ");
        degreeFreq.forEach((k, v) -> {
            System.console().writer().println(v + " nodes have degree " + k);
        });
        System.console().writer().println("Program took " + (System.nanoTime() - 
                startTime)/1000000000 + " seconds");
    }

    /*
     *  Constructs adjacency matrix from current graph.
     */
    public static short[][] constructAdjacencyMatrix(ArrayList<Node> currGraph) {
        short[][] adjacencyMatrix
                = new short[currGraph.size()][currGraph.size()];
        for (short i = 0; i < currGraph.size(); i++) {
            for (short j = 0; j < currGraph.size(); j++) {
                adjacencyMatrix[i][j] = (short) ((currGraph.get(i).hasNeighbor(currGraph.get(j)))
                        ? 1 : 0);
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
            System.out.println("Row: " + i);
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat1.length; k++) {
                    outputMatrix[i][k] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return outputMatrix;
    }
}
