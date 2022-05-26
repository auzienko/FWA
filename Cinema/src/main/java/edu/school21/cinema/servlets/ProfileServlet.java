package edu.school21.cinema.servlets;

import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.models.UserAvatar;
import edu.school21.cinema.services.UserAuthHistoryService;
import edu.school21.cinema.services.UserAvatarService;
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
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserAuthHistoryService userAuthHistoryService;
    private UserAvatarService userAvatarService;
    private static final String JSPURL = "/WEB-INF/jsp/profile.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userAuthHistoryService = springContext.getBean(UserAuthHistoryService.class);
        this.userAvatarService = springContext.getBean(UserAvatarService.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("id");
        Long userAvatarId = (Long) req.getSession().getAttribute("avatarId");
        List<UserAuthHistory> userAuthHistoryList = userAuthHistoryService.getUserAuthHistory(userId);
        List<UserAvatar> userAvatarList = userAvatarService.getByUserId(userId);
        req.setAttribute("userAvatar", "https://html-online.com/editor/images/html-editor.png");
        if (userAvatarId != null) {
            Optional<UserAvatar> userAvatar = userAvatarService.get(userAvatarId);
            userAvatar.ifPresent(avatar -> req.setAttribute("userAvatar", "./images/" + userAvatarId + "---" + avatar.getFileName()));
        }
        req.setAttribute("userAuthHistory", userAuthHistoryList);
        req.setAttribute("userAvatarsHistory", userAvatarList);
        req.getRequestDispatcher(JSPURL).forward(req, resp);
    }
}
