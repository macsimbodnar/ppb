package com.mazerfaker.pewpewboom.controller;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.model.App;
import com.mazerfaker.pewpewboom.model.characters.Ship;
import com.mazerfaker.pewpewboom.model.weapons.Blaster;
import com.mazerfaker.pewpewboom.model.weapons.Weapon;


public class Surface extends SurfaceView implements Callback {

    private static final String TAG = "Surface";


    public Surface(Context context) {
        super(context);
        getHolder().addCallback(this); // Important
        _mainThread = new MainThread(this);

        _app = App.getInstance();

        Weapon blaster = new Blaster(BitmapFactory.decodeResource(getResources(), R.drawable.bullet));
        Ship ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.ship), blaster);

        _app.setShip(ship);
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
        // TODO da implementare
    }


    public void update() {
        _app.update();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        _app.draw(canvas);
    }



    private MainThread _mainThread;
    private App _app;
}
