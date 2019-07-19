package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.User;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AuthCommand implements Command {
    private UserService userService = new UserService();
    private final String LOGIN = "?login=";
    @Override
    public String execute(HttpServletRequest request) {
        LoginUserDTO userDTO = new LoginUserDTO(
                request.getParameter("login"),
                request.getParameter("password"));
        User user = userService.auth(userDTO);
        return PageRoutes.REDIRECT + PageRoutes.USERPAGE + LOGIN + user.getLogin();
    }
}
