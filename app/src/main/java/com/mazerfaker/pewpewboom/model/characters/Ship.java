package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.weapons.Weapon;
import com.mazerfaker.pewpewboom.util.Constants;

public class Ship extends Character implements Drawable {

    private static final String TAG = "Ship";


    public Ship(Bitmap bitmap, Weapon weapon) {
        super(bitmap, weapon, Constants.SHIP_LIFE);

        float initialX = (((float) _app.getWindowWidth()) / 2.0f) - (_bitmap.getWidth() / 2.0f);
        float initialY = ((float) _app.getWindowHeght()) - _bitmap.getHeight() - Constants.SHIP_BOTTOM_PADDING;

        initHitbox(initialX, initialY);
    }


    @Override
    public void update() {
        moveOnX();
        fire();
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    public int getLife() {
        return _life;
    }


    public int takeDamage(int damage) {
        _life -= damage;
        return (_life < 0) ? 0 : _life;
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
            _x += Constants.SHIP_X_SPEED;
            _hitbox.updateX(_x);
        }
    }


    private void moveLeft() {
        if(_x  > 0) {
            _x -= Constants.SHIP_X_SPEED;
            _hitbox.updateX(_x);
        }
    }


    private void fire() {

        Bullet bullet = _weapon.fire(_x + _halfWidth, _y);
        if(bullet != null) {
            _app.addBullet(bullet);
        }
    }
}
