package com.nazar.model.dao.implementations.queries;

import com.nazar.model.dao.implementations.queries.fieldsdb.FoodFields;
import com.nazar.model.dao.implementations.queries.fieldsdb.MealFields;
import com.nazar.model.dao.implementations.queries.fieldsdb.MealFoodFields;

public interface MealSQL{
    String CREATE = "INSERT INTO `"
            + MealFields.TABLE_NAME + "`(`"
            + MealFields.DESCRIPTION + "`, `"
            + MealFields.ADD_TIME + "`, `"
            + MealFields.USER_ID
            + "`) VALUES ((?), (?), (?))";

    String ADDFOODTOMEAL = "INSERT INTO `"
            + MealFoodFields.TABLE_NAME + "`(`"
            + MealFoodFields.FOOD_ID + "`, `"
            + MealFoodFields.MEAL_ID + "`, `"
            + MealFoodFields.FOOD_COUNT
            + "`) VALUES ((?), (?), (?))";
    String FINDBYUSERID= "SELECT * FROM `"
            + MealFields.TABLE_NAME
            + "` WHERE `" + MealFields.USER_ID + "`=(?)";
    String GETFOODFROMMEAL = "SELECT * FROM `"
            + FoodFields.TABLE_NAME + "`JOIN `"
            + MealFoodFields.TABLE_NAME + "` ON `"
            + FoodFields.TABLE_NAME + "`.`" + FoodFields.ID
            + "`=`" +MealFoodFields.TABLE_NAME + "`.`" + MealFoodFields.FOOD_ID
            + "`WHERE `" + MealFoodFields.MEAL_ID + "`=(?)";
}