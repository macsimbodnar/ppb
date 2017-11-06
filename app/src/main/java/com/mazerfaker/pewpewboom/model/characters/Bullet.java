package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface Bullet {

    public void update();
    public void draw(Canvas canvas);
    public int getDamage();
    public Rect getHitbox();
    public int getLifetime();
}

