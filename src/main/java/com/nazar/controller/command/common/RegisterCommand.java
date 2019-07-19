package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.JSPRoutes;
import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private UserService userService = new UserService();
    private final String NOT_UNIQUE = "?notUnique=true";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //todo validation
        try {
            userService.saveNewUser(User.builder()
                    .login(login)
                    .password(password)
                    .build());
        } catch (NotUniqueLoginException e) {
            return JSPRoutes.REGISTRATION + NOT_UNIQUE;
        }
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.MAIN;
    }
}
