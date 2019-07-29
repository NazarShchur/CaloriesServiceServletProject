package com.nazar.service;

import com.nazar.model.dto.userdto.CheckUserDTO;
import com.nazar.model.dto.userdto.RegistrationUserDTO;
import com.nazar.model.entity.Gender;
import com.nazar.model.entity.LifeStyle;
import com.nazar.model.myexceptions.UnacceptableDataInputException;

public class RegistrationService {
    private final String AND ="&";
    private final String EQ = "=";
    private final String LOGINREGEX = "^[a-zA-Z0-9]{3,20}$";
    private final String PASSREGEX = "^[a-zA-Z0-9]{3,20}$";
    private final String CHECKLOGIN = "checkLogin";
    private final String CHECKPASSWORD = "checkPassword";
    private final String CHECKAGE = "checkAge";
    private final String CHECKWEIGHT = "checkWeight";
    private final String CHECKHEIGHT = "checkHeight";
    private final String LOGIN = "login";
    private final String AGE = "age";
    private final String WEIGHT = "weight";
    private final String HEIGHT = "height";
    private final int MINHEIGHT = 40;
    private final int MAXHEIGHT = 250;
    private final int MINWEIGHT = 6;
    private final int MAXWEIGHT = 600;
    private final int MAXAGE = 120;
    private final int MINAGE = 1;
    private boolean isLoginCorrect(String login){
        return login.matches(LOGINREGEX);
    }
    private boolean isPasswordCorrect(String password){
        return password.matches(PASSREGEX);
    }
    private boolean isHeightCorrect(int height){
        return height >= MINHEIGHT && height <= MAXHEIGHT;
    }
    private boolean isWeightCorrect(int weight){
        return weight >= MINWEIGHT && weight <= MAXWEIGHT;
    }
    private boolean isAgeCorrect(int age){
        return age >= MINAGE && age <= MAXHEIGHT;
    }

    public boolean checkIsNotCorrectData(RegistrationUserDTO user){
        return !(isLoginCorrect(user.getLogin())
                && isPasswordCorrect(user.getPassword())
                && isHeightCorrect(user.getHeight())
                && isWeightCorrect(user.getWeight())
                && isAgeCorrect(user.getAge()));
    }
    public String getURLParams(RegistrationUserDTO user){
        return "?"
                + CHECKLOGIN+ EQ + isLoginCorrect(user.getLogin()) + AND + LOGIN + EQ + user.getLogin()
                + AND + CHECKPASSWORD + EQ + isPasswordCorrect(user.getPassword())
                + AND + CHECKHEIGHT + EQ + isHeightCorrect(user.getHeight()) + AND + HEIGHT + EQ + user.getHeight()
                + AND + CHECKWEIGHT + EQ + isWeightCorrect(user.getWeight()) + AND + WEIGHT + EQ + user.getWeight()
                + AND + CHECKAGE + EQ + isAgeCorrect(user.getAge()) + AND + AGE + EQ + user.getAge();
    }
    public RegistrationUserDTO checkIsValidDataAndReturnValidDTO(CheckUserDTO userDTO) throws UnacceptableDataInputException {
        RegistrationUserDTO user = new RegistrationUserDTO();
        try {
            user.setHeight(Integer.parseInt(userDTO.getHeight()));
            user.setWeight(Integer.parseInt(userDTO.getWeight()));
            user.setAge(Integer.parseInt(userDTO.getAge()));
        } catch (Exception e){
            throw new UnacceptableDataInputException(e);
        }
        try {
            user.setLifeStyle(LifeStyle.valueOf(userDTO.getLifeStyle()));
            user.setGender(Gender.valueOf(userDTO.getGender()));
        } catch (Exception e){
            throw new UnacceptableDataInputException(e);
        }
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
