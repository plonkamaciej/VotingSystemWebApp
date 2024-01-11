package com.example.votingsystemwebapp;

import ModelList.VoteSystemModelList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCandidate")
public class AddCandidateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addCandidatePage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String candidateName = request.getParameter("candidateName");
        VoteSystemModelList modelList = VoteSystemModelList.getInstance();
        modelList.addCandidate(candidateName);
        modelList.printToFile();
        response.sendRedirect(request.getContextPath() + "/addCandidate");
    }

}
