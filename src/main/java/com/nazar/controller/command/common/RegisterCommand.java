package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.JSPRoutes;
import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.dto.userdto.RegistrationUserDTO;
import com.nazar.model.entity.Gender;
import com.nazar.model.entity.LifeStyle;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;
import com.nazar.service.RegistrationService;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

public class RegisterCommand implements Command {
    private final String NOT_UNIQUE_LOGIN = "?notUniqueLogin=true";
    private RegistrationService regisrationService = new RegistrationService();
    private UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request) {
        RegistrationUserDTO userDTO = new RegistrationUserDTO();
        userDTO.setLogin(request.getParameter("login"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setAge(Integer.parseInt(request.getParameter("age")));
        userDTO.setLifeStyle(LifeStyle.valueOf(request.getParameter("lifeStyle")));
        userDTO.setGender(Gender.valueOf(request.getParameter("gender")));
        userDTO.setWeight(Integer.parseInt(request.getParameter("weight")));
        userDTO.setHeight(Integer.parseInt(request.getParameter("height")));
        if(regisrationService.checkIsFieldsWrongByRegex(userDTO)){
            System.out.println(regisrationService.getURLParams(userDTO));
            return JSPRoutes.REGISTRATION + regisrationService.getURLParams(userDTO);
        }
        //todo validation
        try {
            userService.saveNewUser(User.builder()
                    .login(userDTO.getLogin())
                    .password(userDTO.getPassword())
                    .age(userDTO.getAge())
                    .height(userDTO.getHeight())
                    .lifeStyle(userDTO.getLifeStyle())
                    .gender(userDTO.getGender())
                    .role(userDTO.getRoles())
                    .build());
        } catch (NotUniqueLoginException e) {
            return JSPRoutes.REGISTRATION + NOT_UNIQUE_LOGIN;
        }
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.MAIN;
    }
}
