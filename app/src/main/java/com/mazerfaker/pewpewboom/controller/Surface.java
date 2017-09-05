package com.mazerfaker.pewpewboom.controller;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.model.characters.Ship;

public class Surface extends SurfaceView implements Callback {

    private static final String TAG = "Surface";


    public Surface(Context context) {
        super(context);
        getHolder().addCallback(this); // Important
        _mainThread = new MainThread(this);

        _ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.ship));
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _mainThread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retray=true;

        while(retray) {
            try{

                _mainThread.join();
                retray = false;

            } catch(InterruptedException e) {
                Log.e(TAG, "SURFACEdESTROYER METHOD - CANT JOIN THE MAINTHRAD");
                e.printStackTrace();
            }
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    public void update() {

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        _ship.draw(canvas);
    }


    private MainThread _mainThread;
    private Ship _ship;
}
