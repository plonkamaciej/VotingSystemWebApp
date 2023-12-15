/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelList;

import Model.VoteSystemModel;
import fileMenager.FileManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Maciej-Plonka
 * @version 1.13
 */
public class VoteSystemModelList {

    /**
     * List of candidates
     */
    private final List<VoteSystemModel> CandidateList;
    /**
     * constructor when no candidate is specified
     */
    public VoteSystemModelList() {

        this.CandidateList = new ArrayList<>();
    }

    /**
     * add candidate to the list
     * @param name of the candidates
     */

    public void addCandidate(String name) throws IOException {
        VoteSystemModel candidate = new VoteSystemModel(name, 0);
        CandidateList.add(candidate);
    }

    /**
     * add candidate to list od candidates
     * @param candidate candidates
     */
    public void addVoteSystemModelList(VoteSystemModel candidate) {

        CandidateList.add(candidate);
    }

    /**
     * @return size of candidate list
     */
    public int size(){
        return CandidateList.size();
    }

    /**
     * vote for candidate with number i
     * @param i user vote
     */
    public void vote(int i){CandidateList.get(i).vote();}

    /**
     * print all candidates
     */
    public void print(){
        IntStream.range(0, CandidateList.size()).mapToObj(i -> i + 1 + ". " + CandidateList.get(i).getName() + " " + CandidateList.get(i).getVoteCount()).forEach(System.out::println);
    }

    /**
     * print all candidates to file
     * @throws IOException error saving file
     */
    public void printToFile() throws IOException {
        StringBuilder content = new StringBuilder();
        for (VoteSystemModel voteSystemModel : CandidateList)
            content.append(voteSystemModel.getName()).append(" ").append(voteSystemModel.getVoteCount()).append("\n");
        FileManager.ToFile(content.toString(), FileManager.getCandidateFile());
    }

    /**
     * delete candidate with specific index 'i'
     * @param i index of candidate
     */
    public void delateCandidate(int i) {
        CandidateList.remove(i);
    }


    public List<VoteSystemModel> getCandidateList() {
        return  CandidateList;
    }

    public void delateCandidateByName(String candidateName) {
        int index;
        for (int i=0; i< CandidateList.size(); i++) {
           if (CandidateList.get(i).getName().equals(candidateName)) {
               index = i;
               CandidateList.remove(index);
               break;
           }
       }
    }

    public void voteByName(String candidateName) {
        int index;
        for (int i=0; i< CandidateList.size(); i++) {
            if (CandidateList.get(i).getName().equals(candidateName)) {
                index = i;
                vote(index);
                break;
            }
        }
    }
}
