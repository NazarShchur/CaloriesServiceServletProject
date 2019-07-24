package com.nazar.model.dao.interfaces;
import com.nazar.model.entity.Food;

import java.util.List;

public interface FoodDao extends CRUDDao<Food>{
    List<Food> findByIsPublic(boolean isPublic);
    List<Food> findByUserID(int id);

}
