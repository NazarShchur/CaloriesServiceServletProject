package com.nazar;

import com.nazar.controller.command.*;
import com.nazar.controller.command.common.*;
import com.nazar.controller.command.routes.PageRoutes;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends javax.servlet.http.HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("registration",
                new RegistrationCommand());
        commands.put("login",
                new LoginCommand());
        commands.put("register",
                new RegisterCommand());
        commands.put("main",
                new MainCommand());
        commands.put("auth",
                new AuthCommand());
        commands.put("userpage",
                new UserPageCommand());
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(".*/app/" , "");
        System.out.println(path);
        Command command = commands.getOrDefault(path ,
                (r)->"main");
        String page = command.execute(request);
        if(page.contains(PageRoutes.REDIRECT)){
            response.sendRedirect(page.replace(PageRoutes.REDIRECT,""));
        }else {
            request.getRequestDispatcher(request.getContextPath() + page).forward(request, response);
        }
    }

}
