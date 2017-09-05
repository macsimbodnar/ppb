package com.mazerfaker.pewpewboom.model;

import android.graphics.RectF;

public class Hitbox {


    public Hitbox(float left, float top, float width, float height) {
        _width = width;
        _height = height;
        _hitbox = new RectF(left, top, left + _width, top + _height);
    }


    public void update(float left, float top) {
        _hitbox.offsetTo(left, top);
    }


    public void updateX(float left) {
        _hitbox.offsetTo(left, _hitbox.top);
    }


    public void updateY(float top) {
        _hitbox.offsetTo(_hitbox.left, top);
    }


    public boolean checkCollision(RectF hitbox){
        return _hitbox.intersect(hitbox);
    }

    private RectF _hitbox;
    private float _width;
    private float _height;
}
