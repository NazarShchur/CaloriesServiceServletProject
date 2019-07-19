package com.nazar.model.dao.implementations.queries;

import com.nazar.model.dao.implementations.queries.fieldsdb.UserFields;

public interface UserSQL {
    String SAVE = "INSERT INTO `" +
            UserFields.TABLE_NAME +
            "`(`" + UserFields.LOGIN + "`, `" +
            UserFields.PASSWORD + "`) VALUES ((?), (?))";
    String FINDBYLOGINANDPASSWORD = "SELECT * FROM `" +
            UserFields.TABLE_NAME +
            "` WHERE `" + UserFields.LOGIN +
            "`=(?)" + " AND `" + UserFields.PASSWORD +
            "`=(?)";
}
