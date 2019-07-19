package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.JSPRoutes;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JSPRoutes.REGISTRATION;
    }
}
