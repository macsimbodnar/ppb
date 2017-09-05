package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.mazerfaker.pewpewboom.model.App;
import com.mazerfaker.pewpewboom.model.Hitbox;
import com.mazerfaker.pewpewboom.util.Constants;

public class Ship implements Character {

    private static final String TAG = "Ship";


    public Ship(Bitmap bitmap) {
        _app = App.getInstance();
        Log.d(TAG, _app.toString());
        _bitmap = bitmap;
        _life = Constants.SHIP_LIFE;
        _x = (((float) _app.getWindowWidth()) / 2.0f) - (_bitmap.getWidth() / 2.0f);
        _y = ((float) _app.getWindowHeght()) - _bitmap.getHeight() - Constants.SHIP_BOTTOM_PADDING;
        _hitbox = new Hitbox(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());
    }


    @Override
    public void update() {
        //Log.d(TAG, "MOVE LEFT = " + _app.isMovingLeft() + "  MOVE RIGHT = " + _app.isMovingRight());
        if(_app.isMovingRight()) {
            moveRight();
        } else if(_app.isMovingLeft()) {
            moveLeft();
        }
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    @Override
    public int getLife() {
        return _life;
    }


    @Override
    public int takeDamage(int damage) {
        _life -= damage;
        return (_life < 0) ? 0 : _life;
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

    private Bitmap _bitmap;
    private float _x;
    private float _y;
    private Hitbox _hitbox;
    private int _life;
    private App _app;
}
