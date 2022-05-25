package edu.school21.cinema.servlets;

import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.services.UserAuthHistoryService;
import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserAuthHistoryService userAuthHistoryService;
    private static final String JSPURL = "/WEB-INF/jsp/profile.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userAuthHistoryService = springContext.getBean(UserAuthHistoryService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserAuthHistory> userAuthHistoryList = userAuthHistoryService.getUserAuthHistory((Long)req.getSession().getAttribute("id"));
        req.setAttribute("userAuthHistory", userAuthHistoryList);
        req.getRequestDispatcher(JSPURL).forward(req, resp);
    }
}
