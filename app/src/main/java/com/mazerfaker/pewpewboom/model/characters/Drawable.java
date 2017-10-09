package com.mazerfaker.pewpewboom.model.characters;


import android.graphics.Canvas;
import android.graphics.RectF;

public interface Drawable {

    public void update();
    public void draw(Canvas canvas);
    //public void reset();
    public RectF getHitbox();
    public boolean hit(int damage);
    public int getPoints();
}
