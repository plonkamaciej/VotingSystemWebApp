/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 * Model used later for list of model - candiates
 * @author Maciej-Plonka
 * @version 1.7
 */

public class VoteSystemModel {
    /**
     * candidate name
     */
    private final String name;

    /**
     * sum of all votes
     */
    private int voteCount;

    /**
     * Constructor for model when parameters are provided
     * @param name
     * @param voteCount
     */
    public VoteSystemModel(String name, int voteCount) {
        if(name == null || name.isBlank() || name.isEmpty()) throw new NullPointerException();
        this.name = name;
        this.voteCount = voteCount;
    }

    /**
     * @return name of signle candidate
     */
    public String getName() {
      return name;
   }

    /**
     * @return numebr of votes that candidate already have
     */
   public int getVoteCount() {
      return voteCount;
   }

    /**
     * increase vote count of candidate
     */
   public void vote(){this.voteCount++;}

    @Override
    public String toString() {
        return "VoteSystemModel{" + "name=" + name + ", voteCount=" + voteCount + '}';
    }

}
