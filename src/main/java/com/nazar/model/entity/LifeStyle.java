package com.nazar.model.entity;

public enum LifeStyle {
    Sitting(1.2),
    LowActivity(1.1375),
    NormalActivity(1.55),
    HighActivity(1.725),
    Sportsman(1.9);

    LifeStyle(double amr){
        this.amr = amr;
    }
    private double amr;
}
