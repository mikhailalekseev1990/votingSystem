package ru.javawebinar.restaurant.web.servlet;

import org.slf4j.Logger;
import ru.javawebinar.restaurant.web.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int u_id = Integer.parseInt(request.getParameter("u_id"));
        SecurityUtil.setAuthu_id(u_id);
        response.sendRedirect("restaurants");
    }
}
