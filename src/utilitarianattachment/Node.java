/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarianattachment;

import java.util.HashSet;
import java.util.Random;

/*
 * @author Dan Giaime
 */
public class Node {

    private double myProbAttach;
    private HashSet<Node> myNeighbors;

    public boolean tryToAdd(Node possNeighbor, Random r) {
        if (r.nextDouble() < getMyProbAttach()) {
            myNeighbors.add(possNeighbor);
            System.out.println("Added a link");
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasNeighbor(Node possNeighbor){
        return myNeighbors.contains(possNeighbor);
    }

    /**
     * @return degree of node
     */
    public int getDegree() {
        return myNeighbors.size();
    }

    /**
     * @return probability of attachment
     */
    public double getMyProbAttach() {
        return myProbAttach;
    }

    /**
     * @return HashSet of neighbors
     */
    public HashSet<Node> getMyNeighbors() {
        return myNeighbors;
    }
}
