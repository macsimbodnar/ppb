package com.mazerfaker.pewpewboom.controller;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;

import com.mazerfaker.pewpewboom.model.Background;
import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.model.characters.Drawable;
import com.mazerfaker.pewpewboom.model.characters.Ship;
import com.mazerfaker.pewpewboom.util.Constants;

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
        _megaBullets = new ArrayList<Bullet>();
        _enemyBullets = new ArrayList<Bullet>();
        _enemies = new ArrayList<Drawable>();
        _gameover = false;
        _megaWeaponCounter = Constants.MEGA_W_RESET; // E' uno perche così il fireButton inizia green e non black
        _megaWeaponReset = Constants.MEGA_W_RESET;
    }



    public static App getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }


    public void update() {

        checkCollisions();

        _background.update();

        _ship.update();

        superWeaponFire();

        for(Bullet b : _megaBullets) {
            b.update();
        }

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

        _background.draw(canvas);

        _ship.draw(canvas);

        //drawDebug(canvas, _ship.getHitbox());

        for(Drawable d : _enemies) {
            d.draw(canvas);

            //drawDebug(canvas, d.getHitbox());
        }

        for(Bullet eb : _enemyBullets) {
            eb.draw(canvas);

            //drawDebug(canvas, eb.getHitbox());
        }

        for(Bullet b : _bullets) {
            b.draw(canvas);

            //drawDebug(canvas, b.getHitbox());
        }

        for(Bullet b : _megaBullets) {
            b.draw(canvas);

            //drawDebug(canvas, b.getHitbox());
        }
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


    public void fire() {
        if( _megaWeaponCounter == _megaWeaponReset) {
            _fire = true;
        }
    }


    public int getWindowHeght() {
        return _windowHeght;
    }


    public void addBullet(Bullet bullet) {
        _bullets.add(bullet);
    }


    public void setShip(Ship ship) {
        _ship = ship;
        _shipTop = ( int ) _ship.getHitbox().top + 2;
    }


    public void addEnemyBullet(Bullet bullet) {
        _enemyBullets.add(bullet);
    }


    public void addEnemy(Drawable drawable) {
        _enemies.add(drawable);
    }


    public boolean isGameOver() {
        return _gameover;
    }


    public void addMegaBullet(Bullet megaBullet) {
        _megaBullets.add(megaBullet);
    }


    public void setFireButton(ConstraintLayout button) {
        _fireButton = button;
    }


    public void setBackground(Background background) {
        _background = background;
    }


    public void resetFireButton() {
        _megaWeaponCounter = Constants.MEGA_W_RESET - 1;
    }


    public void checkFireButton() {
        if(_megaWeaponCounter < _megaWeaponReset) {
            setFireButtonColor(false);
        } else {
            setFireButtonColor(true);
        }
    }


    private void checkCollisions() {
        megaBulletsCollision();
        enemyBulletsCollisons();
        shipBulletsCollisions();
        shipEnemiesCollisions();
    }


    private void megaBulletsCollision() {
        Bullet b;

        for (Iterator<Bullet> iteratorB = _megaBullets.iterator(); iteratorB.hasNext(); ) {
            b = iteratorB.next();

            // check if megabulllet time is end
            if(b.getLifetime() == 0) {
                iteratorB.remove();
                continue;
            }

            for (Iterator<Drawable> iteratorE = _enemies.iterator(); iteratorE.hasNext(); ) {
                Drawable d = iteratorE.next();

                // check collision
                if(RectF.intersects(b.getHitbox(), d.getHitbox())) {
                    if(d.hit(b.getDamage())) {
                        iteratorE.remove();
                    }
                }
            }
        }
    }


    private void enemyBulletsCollisons() {
        Bullet b;
        Drawable d;
        RectF enemyHotbox;
        RectF bulletHitbox;

        for (Iterator<Bullet> iteratorB = _bullets.iterator(); iteratorB.hasNext(); ) {
            b = iteratorB.next();
            bulletHitbox = b.getHitbox();

            // check ship bullet out of screen
            if(bulletHitbox.bottom < 0) {
                iteratorB.remove();
                continue;
            }

            for (Iterator<Drawable> iteratorE = _enemies.iterator(); iteratorE.hasNext(); ) {
                d = iteratorE.next();
                enemyHotbox = d.getHitbox();

                // check collision
                if(RectF.intersects(bulletHitbox, enemyHotbox)) {
                    if(d.hit(b.getDamage())) {
                        iteratorE.remove();
                    }
                    iteratorB.remove();
                    continue;
                }

                // check enemy out of screen
                if(enemyHotbox.top > _windowHeght) {
                    iteratorE.remove();
                }
            }
        }
    }


    private void shipBulletsCollisions() {
        Bullet b;
        RectF shipHitbox = _ship.getHitbox();
        RectF bulletHitbox;

        for (Iterator<Bullet> iterator = _enemyBullets.iterator(); iterator.hasNext(); ) {
            b = iterator.next();
            bulletHitbox = b.getHitbox();

            if(bulletHitbox.bottom < _shipTop) {
                break;
            }

            // check collision
            if(RectF.intersects(shipHitbox, bulletHitbox)) {
                if(_ship.hit(b.getDamage())) {
                    _gameover = true;
                }
                iterator.remove();
                continue;
            }

            // check enemy bullet out of screen
            if(b.getHitbox().top > _windowHeght) {
                iterator.remove();
            }
        }
    }


    private void shipEnemiesCollisions() {

        RectF shipHitbox = _ship.getHitbox();
        RectF enemyHitbox;

        // check collision
        for (Iterator<Drawable> iterator = _enemies.iterator(); iterator.hasNext(); ) {
            enemyHitbox = iterator.next().getHitbox();

            if(enemyHitbox.bottom < _shipTop) {
                break;
            }

            if(RectF.intersects(shipHitbox, enemyHitbox)) {
                _gameover = true;
                iterator.remove();
            }
        }
    }


    private void drawDebug(Canvas c, RectF hitbox) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        c.drawRect(hitbox, paint);
    }


    private void superWeaponFire() {
        if(_fire && _megaWeaponCounter == _megaWeaponReset) {

            _ship.megafire();

            _fire = false;
            _megaWeaponCounter = 0;

            setFireButtonColor(false);
        }

        if(_megaWeaponCounter < _megaWeaponReset) {
            _megaWeaponCounter++;

            if(_megaWeaponCounter == _megaWeaponReset) {
                setFireButtonColor(true);
            }
        }
    }


    private void setFireButtonColor(final boolean active) {

        _fireButton.post(new Runnable() {
            @Override
            public void run() {

                int color;
                if(active) {
                    color = Color.GREEN;
                } else {
                    color = Color.RED;
                }

                _fireButton.setBackgroundColor(color);
            }
        });
    }


    private int _windowWidth;
    private int _windowHeght;
    private ConstraintLayout _fireButton;

    private boolean _moveLeft;
    private boolean _moveRight;
    private boolean _fire;

    private Background _background;
    private Ship _ship;
    private List<Drawable> _enemies;
    private List<Bullet> _bullets;
    private List<Bullet> _megaBullets;
    private List<Bullet> _enemyBullets;
    private boolean _gameover;

    private int _megaWeaponCounter;
    private int _megaWeaponReset;
    private int _shipTop;
}
