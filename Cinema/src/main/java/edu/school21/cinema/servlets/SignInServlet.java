package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.services.UserAuthHistoryService;
import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UsersService usersService;
    private UserAuthHistoryService userAuthHistoryService;
    private static final String JSPURL = "/WEB-INF/jsp/signin.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
        this.userAuthHistoryService = springContext.getBean(UserAuthHistoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPURL).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> user = usersService.signIn(email, password);
        if (user.isPresent()) {
            user.get().toSessionAttributes(req.getSession());
            userAuthHistoryService.save(new UserAuthHistory(LocalDateTime.now(), req.getRemoteAddr(), user.get().getId()));
            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            req.setAttribute("signInError", "signInError");
            req.getRequestDispatcher(JSPURL).forward(req, resp);
        }
    }
}
