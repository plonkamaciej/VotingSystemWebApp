package com.example.votingsystemwebapp;

import fileMenager.FileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/votedUsers")
public class VotedUsersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pobranie listy użytkowników, którzy już głosowali
        List<String> votedUsers = getVotedUsers();

        // Wysłanie odpowiedzi HTML
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Voted Users List</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>List of Users Who Have Voted</h2>");
            out.println("<ul>");

            for (String username : votedUsers) {
                out.println("<li>" + username + "</li>");
            }

            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private List<String> getVotedUsers() throws IOException {
        // Pobranie listy użytkowników z pliku
        File userFile = FileManager.getUserFile();
        List<String> votedUsers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                votedUsers.add(line);
            }
        }

        return votedUsers;
    }
}
