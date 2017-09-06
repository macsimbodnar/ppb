package com.mazerfaker.pewpewboom.model;


import android.graphics.Canvas;
import android.util.Log;

import com.mazerfaker.pewpewboom.model.characters.Ship;
import com.mazerfaker.pewpewboom.model.weapons.Bullet;

import java.util.ArrayList;
import java.util.List;

public class App {

    private static App instance = null;
    private static final String TAG = "App";


    private App() {
        _windowHeght = 0;
        _windowWidth = 0;
        _moveLeft = false;
        _moveRight = false;
        _fire = false;
        _ship = null;
        _bullets = new ArrayList<Bullet>();
    }



    public static App getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }


    public void update() {

        _ship.update();

        for(int i = 0; i < _bullets.size(); i++) {

            if(!_bullets.get(i).update()) {
                _bullets.remove(i);
                i--;
            }


        }

        //Log.d(TAG, "size: " + _bullets.size());
    }


    public void draw(Canvas canvas) {
        _ship.draw(canvas);
        for(Bullet b : _bullets) {
            b.draw(canvas);
        }
    }


    public void setWindowWidth(int width) {
        _windowWidth = width;
    }


    public void setWindowHeght(int height) {
        _windowHeght = height;
    }


    public void setWindowSize(int width, int height) {
        _windowHeght = height;
        _windowWidth = width;
    }


    public int getWindowWidth() {
        return _windowWidth;
    }


    public void moveLeft(boolean moveLeft) {
        _moveLeft = moveLeft;
    }


    public void  moveRight(boolean moveRight) {
        _moveRight = moveRight;
    }


    public boolean isMovingRight() {
        return _moveRight;
    }


    public boolean isMovingLeft() {
        return _moveLeft;
    }


    public boolean isFire() {
        if(_fire) {
            _fire = false;
            return true;
        } else {
            return false;
        }
    }


    public void fire(boolean fire) {
        _fire = fire;
    }


    public int getWindowHeght() {
        return _windowHeght;
    }


    public void addBullet(Bullet bullet) {
        _bullets.add(bullet);
    }


    public void deletBullet(int index) {
        _bullets.remove(index);
    }


    public void setShip(Ship ship) {
        _ship = ship;
    }


    public Ship getShip() {
        return _ship;
    }


    private int _windowWidth;
    private int _windowHeght;

    private boolean _moveLeft;
    private boolean _moveRight;
    private boolean _fire;

    private Ship _ship;
    private List<Bullet> _bullets;
}
