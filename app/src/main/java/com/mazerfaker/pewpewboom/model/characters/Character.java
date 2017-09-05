package com.mazerfaker.pewpewboom.model.characters;


import android.graphics.Canvas;

public interface Character {

    public void update();
    public void draw(Canvas canvas);

    public int getLife();
    public int takeDamage(int damage);
}
