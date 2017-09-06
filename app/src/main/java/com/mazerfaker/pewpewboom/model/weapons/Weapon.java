package com.mazerfaker.pewpewboom.model.weapons;


import com.mazerfaker.pewpewboom.model.characters.Bullet;

public interface Weapon {

    public Bullet fire(float x, float y);
}
