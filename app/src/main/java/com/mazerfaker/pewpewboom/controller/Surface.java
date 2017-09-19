package com.mazerfaker.pewpewboom.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

import com.mazerfaker.pewpewboom.R;
import com.mazerfaker.pewpewboom.model.Background;
import com.mazerfaker.pewpewboom.model.characters.Enemy;
import com.mazerfaker.pewpewboom.model.characters.Ship;
import com.mazerfaker.pewpewboom.model.weapons.Blaster;
import com.mazerfaker.pewpewboom.model.weapons.DefaulMegaWeapon;
import com.mazerfaker.pewpewboom.model.weapons.EnemyBlaster;
import com.mazerfaker.pewpewboom.model.weapons.Weapon;
import com.mazerfaker.pewpewboom.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Surface extends SurfaceView implements Callback {

    private static final String TAG = "Surface";


    public Surface(Context context) {
        super(context);
        getHolder().addCallback(this); // Important
        _mainThread = new MainThread(this);

        _app = App.getInstance();
        _app.checkFireButton();

        Background background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        _app.setBackground(background);

        Weapon blaster = new Blaster(BitmapFactory.decodeResource(getResources(), R.drawable.bullet));

        // TODO da togliere il resize qui e creare delle immagini adatte
        Bitmap laser = BitmapFactory.decodeResource(getResources(), R.drawable.laser);
        Bitmap scaledLaser = Bitmap.createScaledBitmap(laser, laser.getWidth(), _app.getWindowHeght(), true);

        Weapon defaultMegaWeapon = new DefaulMegaWeapon(scaledLaser);

        Ship ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.ship), blaster, defaultMegaWeapon);

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


    public void pause() {
        _mainThread.pause();
    }


    public void resume() {
        synchronized (_mainThread) {
            _mainThread.notify();
        }
    }


    public void stop() {
        // Need to resume before to terminate correctly thread
        resume();
        _mainThread.stopGame();
    }


    private void setEnemies() {

        Weapon enemyBlaster = new EnemyBlaster(BitmapFactory.decodeResource(getResources(), R.drawable.bullet2));
        Enemy enemy = new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemy), enemyBlaster, Constants.SIMPLE_ENEMY_LIFE);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        _app.setEnemyVariety(enemies);
    }

    private final MainThread _mainThread;
    private App _app;
}
