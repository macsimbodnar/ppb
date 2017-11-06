package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.weapons.Weapon;
import com.mazerfaker.pewpewboom.util.Constants;

import java.util.List;

public class Ship extends Character implements Drawable {

    private static final String TAG = "Ship";


    public Ship(Bitmap bitmap, List<Bitmap> animation, Weapon weapon, Weapon megaWeapon) {
        super(bitmap, animation, weapon, Constants.SHIP_LIFE, Constants.SHIP_X_SPEED, 0);

        int initialX = ((_app.getWindowWidth()) / 2) - (_bitmap.getWidth() / 2);
        int initialY = (_app.getWindowHeght()) - _bitmap.getHeight() - Constants.SHIP_BOTTOM_PADDING;

        initHitbox(initialX, initialY);

        _megaWeapon = megaWeapon;
    }


    @Override
    public void update() {
        moveOnX();
        fire();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public boolean afterDeadDraw(Canvas canvas) {
        if(_animationFramesIterator.hasNext()) {
            // TODO da risolvere che la bitmap dell'animazione è sfasata;
            _bitmap = _animationFramesIterator.next();
            return false;
        } else {
            _app.gameOver();
            return true;
        }
    }


    @Override
    public boolean onHit(int damage) {
        return super.onHit(damage);
    }


    @Override
    public void onDead() {
        // TODO
    }

    public void megafire() {
        _app.addMegaBullet(_megaWeapon.fire(_x + _halfWidth, _y));
    }


    private void moveOnX() {
        //Log.d(TAG, "MOVE LEFT = " + _app.isMovingLeft() + "  MOVE RIGHT = " + _app.isMovingRight());
        if(_app.isMovingRight()) {
            moveRight();
        } else if(_app.isMovingLeft()) {
            moveLeft();
        }
    }


    private void moveRight() {
        if((_x + _bitmap.getWidth()) < _app.getWindowWidth()) {
            _x += _speed;
            _hitbox.updateX(_x);
        }
    }


    private void moveLeft() {
        if(_x  > 0) {
            _x -= _speed;
            _hitbox.updateX(_x);
        }
    }


    private void fire() {

        Bullet bullet = _weapon.fire(_x + _halfWidth, _y);
        if(bullet != null) {
            _app.addBullet(bullet);
        }
    }


    private Weapon _megaWeapon;
}
