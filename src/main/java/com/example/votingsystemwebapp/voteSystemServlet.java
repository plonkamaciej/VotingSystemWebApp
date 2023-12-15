package com.example.votingsystemwebapp;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.VoteSystemModel;
import ModelList.VoteSystemModelList;
import fileMenager.FileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Override
    public void init() {
        File candidateFile = FileManager.getCandidateFile();
        modelList = retrieveFromDatabase(candidateFile);
    }

    protected void addCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String candidateName = request.getParameter("candidateName");
        VoteSystemModel newCandidate = new VoteSystemModel(candidateName, 0);
        modelList.addVoteSystemModelList(newCandidate);
        modelList.printToFile();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        for (VoteSystemModel candidate : modelList.getCandidateList()) {
            out.println("<tr>");
            out.println("<td>");
            out.println(candidate.getName());
            out.println("</td>");
            out.println("<td>");
            out.println(candidate.getVoteCount());
            out.println("</td>");
            out.println("<td>");
            out.println("<button onclick=\"deleteCandidate('" + candidate.getName() + "')\">Delete</button>");
            out.println("<button onclick=\"vote('" + candidate.getName() + "')\">Vote</button>");
            out.println("</td>");
            out.println("</tr>");
        }
    }

    private static VoteSystemModelList retrieveFromDatabase(File candidateFile) {
        VoteSystemModelList candidateList = new VoteSystemModelList();
        List<List<String>> Candidates = Reader(candidateFile);
        for (List<String> s : Candidates) {
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }
        return candidateList;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addCandidate".equals(action)) {
            addCandidate(request, response);
        } else if ("delete".equals(action)) {
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

        if (username.equals("test")){
            return;
        }

        modelList.voteByName(candidateName);
        modelList.printToFile();
        processRequest(request, response);
    }

    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String candidateName = request.getParameter("candidateName");


        // Add logic to remove the candidate from the modelList
        // For example, assuming modelList.removeCandidateByName(candidateName) method
        // Update this based on your actual modelList implementation
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
}