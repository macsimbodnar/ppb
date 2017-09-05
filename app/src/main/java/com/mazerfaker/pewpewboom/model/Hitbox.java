package com.mazerfaker.pewpewboom.model;

import android.graphics.RectF;

public class Hitbox {


    public Hitbox(float left, float top, float width, float height) {
        _width = width;
        _height = height;
        _hitbox = new RectF(left, top, left + _width, top + _height);
    }


    public void update(float left, float top) {
        _hitbox.set(left, top,  left + _width, top + _height);
    }


    public boolean checkCollision(RectF hitbox){
        return _hitbox.intersect(hitbox);
    }

    private RectF _hitbox;
    private float _width;
    private float _height;
}
