package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;

import javax.servlet.http.HttpServletRequest;

public class AddFoodCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JSPRoutes.ADD_FOOD;
    }
}
