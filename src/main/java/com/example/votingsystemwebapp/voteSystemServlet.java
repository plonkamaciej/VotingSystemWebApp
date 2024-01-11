package com.example.votingsystemwebapp;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.VoteSystemModel;
import ModelList.VoteSystemModelList;
import fileMenager.FileManager;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static fileMenager.FileManager.Reader;

/**
 *
 * @author Maciej
 */@WebServlet("/voteSystem")
public class voteSystemServlet extends HttpServlet {

    private VoteSystemModelList modelList;
    private static final long serialVersionUID = 1L;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        File candidateFile = FileManager.getCandidateFile();
        modelList = VoteSystemModelList.getInstance();
        retrieveFromDatabase(candidateFile);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        for (VoteSystemModel candidate : modelList.getCandidateList()) {

            out.println("<tr class='align-items-center'>");
            out.println("<td class='align-middle'>" + candidate.getName() + "</td>");
            out.println("<td class='align-middle'>" + candidate.getVoteCount() + "</td>");
            out.println("<td class='align-middle'>");
            out.println("<button class='btn btn-danger' onclick=\"deleteCandidate('" + candidate.getName() + "')\">Delete</button>");
            out.println("<button class='btn btn-success' onclick=\"vote('" + candidate.getName() + "')\">Vote</button>");
            out.println("</td>");
            out.println("</tr>");

        }

    }

    private static void retrieveFromDatabase(File candidateFile) {
        VoteSystemModelList candidateList = VoteSystemModelList.getInstance();
        List<List<String>> Candidates = Reader(candidateFile);
        for (List<String> s : Candidates) {
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteCandidate(request, response);
        } else if ("vote".equals(action)) {
            vote(request, response);
        } else {
            processRequest(request, response);
        }
    }

    private void vote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String candidateName = request.getParameter("candidateName");
        String username = request.getParameter("username");

        if (candidateName == null || username == null || candidateName.isEmpty() || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid input data");
        }

        HttpSession session = request.getSession(true);

        List<List<String>> users = FileManager.Reader(FileManager.getUserFile());

        if(!verifyUser(username, users)) {
            modelList.voteByName(candidateName);
            modelList.printToFile();
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            session.setAttribute("exceptionMessage", "You have already voted. Each user is allowed to vote only once.");
        }

        // Set a cookie with the last voted username
        Cookie lastVotedUsernameCookie = new Cookie("lastVotedUsername", username);
        response.addCookie(lastVotedUsernameCookie);

        session.setAttribute("lastVotedCandidate", candidateName);
        processRequest(request, response);

    }

    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String candidateName = request.getParameter("candidateName");


        modelList.delateCandidateByName(candidateName);

        // Save the updated list to the file
        modelList.printToFile();

        // Update the table content after deleting a candidate
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Vote System Servlet";
    }

    public static boolean verifyUser(String user, List<List<String>> users) {
        if(findUser(user, users)){
            System.out.println("You have already voted");
            return true;
        }
        else {
            try {
                FileManager.addToFile(user, FileManager.getUserFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static boolean findUser(String user, List<List<String>> users){
        boolean found = false;
        for (List<String> innerList : users) {
            if (innerList.contains(user)) {
                found =  true;
                break;
            }
        }
        return found;
    }

}