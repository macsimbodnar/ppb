package com.mazerfaker.pewpewboom.controller;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.mazerfaker.pewpewboom.util.Constants;

public class MainThread extends Thread {

    private static final String TAG = "MainThread";


    public MainThread(Surface surface){
        super();
        _surface = surface;
        _surfaceHolder = surface.getHolder();
        _isRunning = true;
        _pause = false;
    }


    @Override
    public void run() {

        Canvas canvas;
        App app = App.getInstance();

        synchronized (this) {
            while (_isRunning) {

                if (_pause) {
                    try {
                        _pause = false;
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Error to set game in pause");
                    }
                }

                canvas = null;

                // main game actions update and draw
                try {

                    canvas = _surfaceHolder.lockCanvas();
                    //synchronized (_surfaceHolder) {
                      //  synchronized (App.getInstance()) {

                            _surface.update();
                            _surface.draw(canvas);

                            if (app.isGameOver()) {
                                return;
                            }
                        //}

                    //}

                } catch (Exception e) {

                    Log.e(TAG, "Error canvas");
                    e.printStackTrace();

                } finally {

                    if (canvas != null) {
                        _surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }


    public void stopGame() {
        _isRunning = false;
    }


    public void pause() {
        _pause = true;
    }


    private final SurfaceHolder _surfaceHolder;
    private Surface _surface;
    private boolean _pause;
    private boolean _isRunning;
}
