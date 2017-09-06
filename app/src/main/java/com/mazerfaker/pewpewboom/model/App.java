package com.mazerfaker.pewpewboom.model;


import android.graphics.Canvas;
import android.graphics.RectF;

import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.model.characters.Drawable;
import com.mazerfaker.pewpewboom.model.characters.Ship;

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
        _enemyBullets = new ArrayList<Bullet>();
        _enemies = new ArrayList<Drawable>();
    }



    public static App getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }


    public void update() {

        checkCollisions();

        _ship.update();

        for(Drawable d : _enemies) {
            d.update();
        }

        for(int i = 0; i < _bullets.size(); i++) {

            if(!_bullets.get(i).update()) {
                _bullets.remove(i);
                i--;
            }


        }

        for (int i = 0; i < _enemyBullets.size(); i++) {

            if(!_enemyBullets.get(i).update()) {
                _enemyBullets.remove(i);
                i--;
            }
        }

        //Log.d(TAG, "size: " + _bullets.size());
    }


    public void draw(Canvas canvas) {
        _ship.draw(canvas);

        for(Drawable d : _enemies) {
            d.draw(canvas);
        }

        for(Bullet b : _bullets) {
            b.draw(canvas);
        }

        for(Bullet eb : _enemyBullets) {
            eb.draw(canvas);
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


    public void addEnemyBullet(Bullet bullet) {
        _enemyBullets.add(bullet);
    }


    public void addEnemy(Drawable drawable) {
        _enemies.add(drawable);
    }


    private void checkCollisions() {
        enemyBulletsCollisons();
        shipBulletsCollisions();
        shipEnemiesCollisions();
    }


    private void enemyBulletsCollisons() {
        for(int i = 0; i < _bullets.size(); i++) {
            for(int j = 0; j < _enemies.size(); j++) {

                if(_bullets.get(i).getHitbox().intersect(_enemies.get(j).getHitbox())) {
                    _bullets.remove(i);
                    i--;
                    _enemies.remove(j);
                    j--;
                    // TODO da fare i danni
                }
            }
        }
    }


    private void shipBulletsCollisions() {
        for(int i = 0; i < _enemyBullets.size(); i++) {
            if(_ship.getHitbox().intersect(_enemyBullets.get(i).getHitbox())) {
                _enemyBullets.remove(i);
                i--;
                // TODO da fare i danni
            }
        }
    }


    private void shipEnemiesCollisions() {
        for(int i = 0; i < _enemies.size(); i++) {
            if(_ship.getHitbox().intersect(_enemies.get(i).getHitbox())) {
                _enemies.remove(i);
                i--;
                // TODO da fare i danni
            }
        }
    }


    private int _windowWidth;
    private int _windowHeght;

    private boolean _moveLeft;
    private boolean _moveRight;
    private boolean _fire;

    private Ship _ship;
    private List<Drawable> _enemies;
    private List<Bullet> _bullets;
    private List<Bullet> _enemyBullets;

}
