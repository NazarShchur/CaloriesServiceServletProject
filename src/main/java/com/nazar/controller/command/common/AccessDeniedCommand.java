package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;

import javax.servlet.http.HttpServletRequest;

public class AccessDeniedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JSPRoutes.ACCESSDENIED;
    }
}
