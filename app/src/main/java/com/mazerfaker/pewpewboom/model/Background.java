package com.mazerfaker.pewpewboom.model;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.controller.App;
import com.mazerfaker.pewpewboom.util.Constants;

public class Background {


    public Background(Bitmap bitmap) {

        App app = App.getInstance();
        // TODO da rimuovere il resize. Non sono sicuro serva se si sceglie bene il background
        // Ratio formula: (original height / original width) x new width = new height
        //float ratioHeight = (((float) bitmap.getHeight()) / ((float) bitmap.getWidth())) * _app.getWindowWidth();
        _bitmap = Bitmap.createScaledBitmap(bitmap, app.getWindowWidth(), app.getWindowHeght(), true);

        _backgroundSpeed = Constants.INIT_BACKGROUN_SPEED;
        _y = 0;

        _bitmapHeight = _bitmap.getHeight();
    }


    public void update() {
        _y += _backgroundSpeed;

        if(Math.abs(_y) > _bitmapHeight) {
            _y -= _bitmapHeight;
        }
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, .0f, _y, null);
        canvas.drawBitmap(_bitmap, .0f, _y - _bitmapHeight, null);
    }


    private float _y;
    private Bitmap _bitmap;
    private int _bitmapHeight;
    private int _backgroundSpeed;
}
