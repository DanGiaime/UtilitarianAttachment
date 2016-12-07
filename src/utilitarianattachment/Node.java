/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarianattachment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/*
 * @author Dan Giaime
 */
public class Node {

    private double myProbAttach;
    private HashSet<Node> myNeighbors;

    public Node(){
        myProbAttach = 1;
        myNeighbors = new HashSet<>();
    }
    
    public boolean tryToAdd(Node possNeighbor, Random r) {
        if (r.nextDouble() < myProbAttach*possNeighbor.getProbAttach()) {
            myNeighbors.add(possNeighbor);
            possNeighbor.add(this);
            System.out.println("Added a link, node now has degree " + myNeighbors.size());
            calcProb();
            return true;
        } else {
            return false;
        }
    }
    
    public void calcProb(){
        System.out.println(myProbAttach);
        myProbAttach = 1-(((myNeighbors.size()+1)*
                Math.pow(Math.E, -(myNeighbors.size()+1)/150))/(myNeighbors.size()+2) - 
                (myNeighbors.size()*
                Math.pow(Math.E, -myNeighbors.size()/150))/(myNeighbors.size()+1));
        System.out.println(myProbAttach);
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
