package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Canvas;
import android.graphics.RectF;

public interface Bullet {

    public void update();
    public void draw(Canvas canvas);
    public int getDamage();
    public RectF getHitbox();
    public int getLifetime();
}

