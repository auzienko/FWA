package edu.school21.cinema.servlets;

import edu.school21.cinema.config.AppConfig;
import edu.school21.cinema.models.UserAvatar;
import edu.school21.cinema.services.UserAuthHistoryService;
import edu.school21.cinema.services.UserAvatarService;
import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@WebServlet("/images/*")
@MultipartConfig()
public class ImagesServlet extends HttpServlet {
    private UserAvatarService userAvatarService;
    private UsersService usersService;

    private static final String JSPURL = "/WEB-INF/jsp/image.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userAvatarService = springContext.getBean(UserAvatarService.class);
        this.usersService = springContext.getBean(UsersService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("newAvatar");
        Long userId = (Long) req.getSession().getAttribute("id");
        String pathToImg = userAvatarService.getPathToSave(userId);
        Files.createDirectories(Paths.get(pathToImg));
        UserAvatar userAvatar = new UserAvatar(part.getSubmittedFileName(), part.getSize(), part.getContentType(), userId);
        Long avatarId = userAvatarService.add(userAvatar);
        part.write(pathToImg + avatarId + "---" + part.getSubmittedFileName());
        usersService.setAvatar(userId, avatarId);
        req.getSession().setAttribute("avatarId", avatarId);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        Long userId = (Long) req.getSession().getAttribute("id");
        if (!uri.startsWith("/images/") || userId == null) {
            return;
        }
        String relativeImagePath = java.net.URLDecoder.decode(uri.substring("/images/".length()), StandardCharsets.UTF_8.name());
        resp.setContentType("image/jpg");
        String pathToImg = userAvatarService.getPathToSave(userId);
        ServletOutputStream outStream;
        outStream = resp.getOutputStream();
        FileInputStream fin = new FileInputStream(pathToImg + relativeImagePath);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        int ch = 0;
        while ((ch = bin.read()) != -1)
            bout.write(ch);
        bin.close();
        fin.close();
        bout.close();
        outStream.close();
    }
}
