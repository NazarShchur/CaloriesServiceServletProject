package com.nazar.utility;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuthUtility{

    public static boolean isUserLogged(HttpServletRequest request){
        return request.getSession().getAttribute("user")!=null;
    }
}
