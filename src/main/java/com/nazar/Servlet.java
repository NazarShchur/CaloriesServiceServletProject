package com.nazar;

import com.nazar.controller.command.*;
import com.nazar.controller.command.common.*;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.controller.command.user.LogOutCommand;
import com.nazar.service.FoodService;
import com.nazar.service.MealService;

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

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("registration",
                new RegistrationCommand());
        commands.put("login",
                new LoginCommand());
        commands.put("logout",
                new LogOutCommand());
        commands.put("register",
                new RegisterCommand());
        commands.put("main",
                new MainCommand());
        commands.put("auth",
                new AuthCommand());
        commands.put("userpage",
                new UserPageCommand());
        commands.put("accessdenied",
                new AccessDeniedCommand());
        commands.put("userpage/newmeal",
                new NewMealCommand(new FoodService()));
        commands.put("userpage/newmeal/addfoodtomeal",
                new AddFoodToMealCommand(new FoodService()));
        commands.put("userpage/newmeal/deletefoodfrommeal",
                new DeleteFoodFromMealCommand(new FoodService()));
        commands.put("userpage/newmeal/savemeal",
                new SaveMealCommand(new MealService()));
        commands.put("userpage/allmeals",
                new AllMealsCommand(new MealService()));

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
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path, new MainCommand());
        String page = command.execute(request);
        if (page.contains(PageRoutes.REDIRECT)) {
            System.out.println("Redirecting to " + page);//todo remove 2
            response.sendRedirect(page.replace(PageRoutes.REDIRECT, ""));

        } else {
            System.out.println("Forwarding to " + page);
            request.getRequestDispatcher(request.getContextPath() + page).forward(request, response);
        }
    }

}
