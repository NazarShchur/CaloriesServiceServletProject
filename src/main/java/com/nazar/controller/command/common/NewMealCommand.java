package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.JSPRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class NewMealCommand implements Command {
    private final String AVAILABLEFOOD = "availableFood";
    private final String CURRENTMAP = "currentMap";
    private FoodService foodService;

    public NewMealCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentUserID = (int) request.getSession().getAttribute("userid");
        FoodCountMapDTO currentMap = Optional.ofNullable((FoodCountMapDTO) request.getSession().getAttribute(CURRENTMAP))
                .orElse(new FoodCountMapDTO());
        List<Food> availableFood = foodService.getAvailableFoodList(currentMap.getMap(), currentUserID);
        request.getSession().setAttribute(AVAILABLEFOOD, availableFood);
        return JSPRoutes.NEWMEAL;
    }
}
