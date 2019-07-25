package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.userdto.CheckUserDTO;
import com.nazar.model.dto.userdto.RegistrationUserDTO;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;
import com.nazar.model.myexceptions.UnacceptableDataInputException;
import com.nazar.service.RegistrationService;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;


public class RegisterCommand implements Command {
    private final String NOT_UNIQUE_LOGIN = "?notUniqueLogin=true";
    private final String UNACCAPTABLEDATA = "?unacceptableData=true";
    private RegistrationService regisrationService = new RegistrationService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        CheckUserDTO userDTO = new CheckUserDTO();
        userDTO.setLogin(request.getParameter("login"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setAge(request.getParameter("age"));
        userDTO.setLifeStyle(request.getParameter("lifeStyle"));
        userDTO.setGender(request.getParameter("gender"));
        userDTO.setWeight(request.getParameter("weight"));
        userDTO.setHeight(request.getParameter("height"));
        RegistrationUserDTO validUser;
        try {
            validUser = regisrationService.checkIsValidDataAndReturnValidDTO(userDTO);
        } catch (UnacceptableDataInputException e){
            return JSPRoutes.REGISTRATION + UNACCAPTABLEDATA;
        }
        if(regisrationService.checkIsNotCorrectData(validUser)){
            return JSPRoutes.REGISTRATION + regisrationService.getURLParams(validUser);
        }
        try {
            userService.saveNewUser(User.builder()
                    .login(validUser.getLogin())
                    .password(validUser.getPassword())
                    .age(validUser.getAge())
                    .weight(validUser.getWeight())
                    .height(validUser.getHeight())
                    .lifeStyle(validUser.getLifeStyle())
                    .gender(validUser.getGender())
                    .build());
        } catch (NotUniqueLoginException e) {
            return JSPRoutes.REGISTRATION + NOT_UNIQUE_LOGIN;
        }
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.MAIN;
    }
}
