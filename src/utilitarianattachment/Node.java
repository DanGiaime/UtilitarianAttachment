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

    public Node(){
        myProbAttach = .1;
        myNeighbors = new HashSet<Node>();
    }
    
    public boolean tryToAdd(Node possNeighbor, Random r) {
        if (r.nextDouble() < getProbAttach()) {
            System.out.println(myNeighbors.add(possNeighbor));
            possNeighbor.getNeighbors().add(this);
            System.out.println("Added a link, node now has degree " + myNeighbors.size());
            calcProb();
            return true;
        } else {
            return false;
        }
    }
    
    public void calcProb(){
        myProbAttach = (myNeighbors.size()*Math.pow(Math.E, myNeighbors.size()*150))/150; 
    }
    
    public void add(Node n){
        myNeighbors.add(n);
        n.getNeighbors().add(this);
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
    public double getProbAttach() {
        return myProbAttach;
    }

    /**
     * @return HashSet of neighbors
     */
    public HashSet<Node> getNeighbors() {
        return myNeighbors;
    }
}
