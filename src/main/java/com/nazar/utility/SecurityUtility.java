package com.nazar.utility;

import com.nazar.config.SecurityConfig;
import javax.servlet.http.HttpServletRequest;

public class SecurityUtility {
    public static boolean isPageSecured(HttpServletRequest request){
        return SecurityConfig.getSecuredPages().containsValue(request.getPathInfo());
    }
}
