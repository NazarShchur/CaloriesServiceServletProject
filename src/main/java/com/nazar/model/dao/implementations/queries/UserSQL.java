package com.nazar.model.dao.implementations.queries;

import com.nazar.model.dao.implementations.queries.fieldsdb.UserFields;
import com.nazar.model.dao.implementations.queries.fieldsdb.UserRolesFields;


public interface UserSQL {
    String SAVE = "INSERT INTO `"
            + UserFields.TABLE_NAME + "`(`"
            + UserFields.LOGIN + "`, `"
            + UserFields.PASSWORD + "`, `"
            + UserFields.AGE + "`, `"
            + UserFields.GENDER + "`, `"
            + UserFields.HEIGHT + "`, `"
            + UserFields.WEIGHT + "`, `"
            + UserFields.LIFE_STYLE
            + "`) VALUES ((?), (?), (?), (?), (?), (?), (?))";

    String FINDBYLOGINANDPASSWORD = "SELECT * FROM `"
            + UserFields.TABLE_NAME
            + "` WHERE `" + UserFields.LOGIN
            + "`=(?)" + " AND `" + UserFields.PASSWORD
            + "`=(?)";

    String FINDROLESBYID = "SELECT `" + UserRolesFields.ROLE //todo merge
            + "` FROM `" + UserRolesFields.TABLE_NAME
            + "` WHERE `" + UserRolesFields.USER_ID + "`=(?)";

    String ADDUSERROLE = "INSERT INTO `"
            + UserRolesFields.TABLE_NAME + "`(`"
            + UserRolesFields.ROLE + "`, `"
            + UserRolesFields.USER_ID
            + "`) VALUES ((?), (?))";
    String FINDIDBYLOGIN = "SELECT `" + UserFields.ID
            + "` FROM `" + UserFields.TABLE_NAME
            + "` WHERE `" + UserFields.LOGIN + "`=(?)";

}
