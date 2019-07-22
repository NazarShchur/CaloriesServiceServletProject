package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.JSPRoutes;
import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.dto.userdto.RegistrationUserDTO;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;
import com.nazar.service.RegistrationService;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private final String NOT_UNIQUE_LOGIN = "?notUniqueLogin=true";
    private RegistrationService regisrationService = new RegistrationService();
    private UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        RegistrationUserDTO userDTO = new RegistrationUserDTO();
        userDTO.setLogin(login);
        userDTO.setPassword(password);
        if(regisrationService.checkIsFieldsWrongByRegex(userDTO)){
            System.out.println(regisrationService.getURLParams(userDTO));
            return JSPRoutes.REGISTRATION + regisrationService.getURLParams(userDTO);
        }
        //todo validation
        try {
            userService.saveNewUser(User.builder()
                    .login(login)
                    .password(password)
                    .build());
        } catch (NotUniqueLoginException e) {
            return JSPRoutes.REGISTRATION + NOT_UNIQUE_LOGIN;
        }
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.MAIN;
    }
}
