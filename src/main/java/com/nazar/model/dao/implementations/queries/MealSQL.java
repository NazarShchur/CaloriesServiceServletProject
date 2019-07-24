package com.nazar.model.dao.implementations.queries;

import com.nazar.model.dao.implementations.queries.fieldsdb.MealFields;

public interface MealSQL{
    String CREATE = "INSERT INTO `"
            + MealFields.TABLE_NAME + "`(`"
            + MealFields.DESCRIPTION + "`, `"
            + MealFields.ADD_TIME + "`, `"
            + MealFields.USER_ID
            + "`) VALUES ((?), (?), (?))";
}
