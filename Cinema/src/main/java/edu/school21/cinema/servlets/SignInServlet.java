package edu.school21.cinema.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signIn")
public class SignInServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(req, resp);
    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //super.doPost(req, resp);
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//
//        RequestDispatcher dis = req.getRequestDispatcher("ProfileServlet");
//        dis.forward(req, resp);
//    }
}
