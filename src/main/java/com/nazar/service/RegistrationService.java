package com.nazar.service;

import com.nazar.model.dto.userdto.RegistrationUserDTO;

public class RegistrationService {
    private final String AND ="&";
    private final String EQ = "=";
    private final String REGEX = "^[a-z]{1,20}$";
    private final String REGEXLOGIN = "regexLogin";
    private final String REGEXPASSWORD = "regexPassword";

    public boolean checkIsFieldsWrongByRegex(RegistrationUserDTO user){
        return !(user.getLogin().matches(REGEX)
                && user.getPassword().matches(REGEX));
    }
    public String getURLParams(RegistrationUserDTO user){
        return "?"
                + REGEXLOGIN + EQ + user.getLogin().matches(REGEX)
                + AND
                + REGEXPASSWORD + EQ + user.getPassword().matches(REGEX);
    }
}
