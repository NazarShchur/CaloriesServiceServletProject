package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.CheckFoodDTO;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;
import com.nazar.model.myexceptions.UnacceptableDataInputException;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;


public class SaveFoodCommand implements Command {
    private final String CARBOHYDRATE = "carbohydrate";
    private final String FOODNAME = "foodName";
    private final String FATS = "fats";
    private final String PROTEIN = "protein";
    private final String UNACCAPTABLE_DATA = "?unacceptableData=true";
    private final String USER_ID = "userid";
    private FoodService foodService;

    public SaveFoodCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String execute(HttpServletRequest request)
    {
        CheckFoodDTO check= new CheckFoodDTO();
        check.setCarbohydrate(request.getParameter(CARBOHYDRATE));
        check.setFats(request.getParameter(FATS));
        check.setProtein(request.getParameter(PROTEIN));
        check.setName(request.getParameter(FOODNAME));
        PrivateFood privateFood;
        try{
            privateFood = foodService.checkIsValidDataAndReturnFood(check);
        } catch (UnacceptableDataInputException e){
            return JSPRoutes.ADD_FOOD + UNACCAPTABLE_DATA;
        }
        foodService.savePrivate(PrivateFood.builder()
                .userID((int)request.getSession().getAttribute(USER_ID))
                .carbohydrate(privateFood.getCarbohydrate())
                .name(privateFood.getName())
                .protein(privateFood.getProtein())
                .fats(privateFood.getFats())
                .build());
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.ADD_FOOD;
    }
}
