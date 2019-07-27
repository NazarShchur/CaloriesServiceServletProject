package com.nazar.model.dao.implementations.queries;

import com.nazar.model.dao.implementations.queries.fieldsdb.FoodFields;

public interface FoodSQL {
    String FIND_ALL = "SELECT * FROM `" + FoodFields.TABLE_NAME + "`";

    String FIND_PUBLIC = "SELECT * FROM `" + FoodFields.TABLE_NAME + "`"
            + "WHERE `" + FoodFields.USER_ID + "` IS NULL";

    String FIND_BY_USER_ID = "SELECT * FROM `" + FoodFields.TABLE_NAME
            + "`WHERE`" + FoodFields.USER_ID + "`=(?)";

    String FIND_BY_ID = "SELECT * FROM `" + FoodFields.TABLE_NAME
            + "`WHERE`" + FoodFields.ID + "`=(?)";

    String SAVE = "INSERT INTO `"
            + FoodFields.TABLE_NAME + "`(`"
            + FoodFields.CARBOHYDRATE + "`, `"
            + FoodFields.FATS + "`, `"
            + FoodFields.PROTEIN + "`, `"
            + FoodFields.NAME
            + "`) VALUES ((?), (?), (?), (?))";
    String SAVE_PRIVATE = "INSERT INTO `"
            + FoodFields.TABLE_NAME + "`(`"
            + FoodFields.USER_ID + "`, `"
            + FoodFields.CARBOHYDRATE + "`, `"
            + FoodFields.FATS + "`, `"
            + FoodFields.PROTEIN + "`, `"
            + FoodFields.NAME
            + "`) VALUES ((?), (?), (?), (?), (?))";
    String FIND_PRIVATE = "SELECT * FROM `" + FoodFields.TABLE_NAME + "`"
            + "WHERE `" + FoodFields.USER_ID + "` IS NOT NULL";

    String UPDATE_TO_PUBLIC = "UPDATE `" + FoodFields.TABLE_NAME
            + "` SET `" + FoodFields.USER_ID + "` = NULL WHERE `"
            + FoodFields.ID + "` = (?)";
}
