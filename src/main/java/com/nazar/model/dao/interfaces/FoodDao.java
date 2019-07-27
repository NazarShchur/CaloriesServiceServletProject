package com.nazar.model.dao.interfaces;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;

import java.util.List;

public interface FoodDao extends CRUDDao<Food>{
    List<Food> findPublicFood();
    List<PrivateFood> findByUserID(int id);
    void savePrivate(PrivateFood food);
    List<PrivateFood> findPrivate();
    void updateToPublic(int id);

}
