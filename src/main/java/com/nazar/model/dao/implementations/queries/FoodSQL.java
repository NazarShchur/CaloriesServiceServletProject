package com.nazar.model.dao.implementations.queries;

import com.nazar.model.dao.implementations.queries.fieldsdb.FoodFields;

public interface FoodSQL {
    String FINDALL = "SELECT * FROM `" + FoodFields.TABLE_NAME + "`";
    String FINDBYISPUBLIC = "SELECT * FROM `" + FoodFields.TABLE_NAME + "`"
            + "WHERE `" + FoodFields.ISPUBLIC + "`=(?)";
    String FINDBYUSERID = "SELECT * FROM `" + FoodFields.TABLE_NAME
            + "`JOIN`" + FoodFields.USER_FOOD_JOIN_TABLE
            + "`ON `" + FoodFields.TABLE_NAME + "`.`" + FoodFields.ID
            + "`=`" + FoodFields.USER_FOOD_JOIN_TABLE + "`.`" + FoodFields.FOOD_ID
            + "`AND`" + FoodFields.USER_FOOD_JOIN_TABLE + "`.`" + FoodFields.USER_ID +"`=(?)";
}
