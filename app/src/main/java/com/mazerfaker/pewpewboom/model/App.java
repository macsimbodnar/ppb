package com.mazerfaker.pewpewboom.model;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.model.characters.Drawable;
import com.mazerfaker.pewpewboom.model.characters.Ship;

import java.util.ArrayList;
import java.util.Iterator;
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
        _gameover = false;
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

        for(Bullet b : _bullets) {
            b.update();
        }

        for(Bullet b : _enemyBullets) {
            b.update();
        }

        //Log.d(TAG, "SHIP BULLETS: " + _bullets.size());
        //Log.d(TAG, "ENEMY BULLETS: " + _enemyBullets.size());
        //Log.d(TAG, "SHIP ENEMIES: " + _enemies.size());
    }


    public void draw(Canvas canvas) {
        _ship.draw(canvas);

        //drawDebug(canvas, _ship.getHitbox());

        for(Drawable d : _enemies) {
            d.draw(canvas);

            //drawDebug(canvas, d.getHitbox());
        }

        for(Bullet b : _bullets) {
            b.draw(canvas);

            //drawDebug(canvas, b.getHitbox());
        }

        for(Bullet eb : _enemyBullets) {
            eb.draw(canvas);

            //drawDebug(canvas, eb.getHitbox());
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
        for (Iterator<Bullet> iteratorB = _bullets.iterator(); iteratorB.hasNext(); ) {
            Bullet b = iteratorB.next();

            // check ship bullet out of screen
            if(b.getHitbox().bottom < 0) {
                iteratorB.remove();
                continue;
            }

            for (Iterator<Drawable> iteratorE = _enemies.iterator(); iteratorE.hasNext(); ) {
                Drawable d = iteratorE.next();

                // check enemy out of screen
                if(d.getHitbox().top > _windowHeght) {
                    iteratorE.remove();
                    continue;
                }

                // check collision
                if(RectF.intersects(b.getHitbox(), d.getHitbox())) {
                    if(d.hit(b.getDamage())) {
                        iteratorE.remove();
                    }
                    iteratorB.remove();
                }
            }
        }
    }


    private void shipBulletsCollisions() {
        for (Iterator<Bullet> iterator = _enemyBullets.iterator(); iterator.hasNext(); ) {
            Bullet b = iterator.next();

            // check enemy bullet out of screen
            if(b.getHitbox().top > _windowHeght) {
                iterator.remove();
                continue;
            }

            // check collision
            if(RectF.intersects(_ship.getHitbox(), b.getHitbox())) {
                if(_ship.hit(b.getDamage())) {
                    _gameover = true;
                }
                Log.d(TAG, "HIT");
                iterator.remove();
            }
        }
    }


    private void shipEnemiesCollisions() {

        // check collision
        for (Iterator<Drawable> iterator = _enemies.iterator(); iterator.hasNext(); ) {
            Drawable d = iterator.next();
            if(RectF.intersects(_ship.getHitbox(), d.getHitbox())) {
                _gameover = true;
                iterator.remove();
            }
        }
    }


    public boolean isGameOver() {
        return _gameover;
    }


    public void drawDebug(Canvas c, RectF hitbox) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        c.drawRect(hitbox, paint);
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
    private boolean _gameover;

}
