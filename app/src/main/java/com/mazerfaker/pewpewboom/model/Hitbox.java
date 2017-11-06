package com.mazerfaker.pewpewboom.model;

import android.graphics.Rect;

public class Hitbox {


    public Hitbox(int left, int top, int width, int height) {
        _hitbox = new Rect(left, top, left + width, top + height);
    }


    public void update(int left, int top) {
        _hitbox.offsetTo(left, top);
    }


    public void updateX(int left) {
        _hitbox.offsetTo(left, _hitbox.top);
    }


    public void updateY(int top) {
        _hitbox.offsetTo(_hitbox.left, top);
    }


    public Rect getRect() {
        return _hitbox;
    }


    private Rect _hitbox;
}
