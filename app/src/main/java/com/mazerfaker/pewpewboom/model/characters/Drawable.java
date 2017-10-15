package com.mazerfaker.pewpewboom.model.characters;


import android.graphics.Canvas;
import android.graphics.RectF;

public interface Drawable {

    public void update();
    public void draw(Canvas canvas);
    public boolean afterDeadDraw(Canvas canvas);
    public RectF getHitbox();
    public boolean onHit(int damage);
    public void onDead();
//    public int getPoints();
}
