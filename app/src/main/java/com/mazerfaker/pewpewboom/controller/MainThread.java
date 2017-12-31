package com.mazerfaker.pewpewboom.controller;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private static final String TAG = "MainThread";


    public MainThread(Surface surface){
        super();
        _surface = surface;
        _surfaceHolder = surface.getHolder();
        _isRunning = true;
        _pause = false;
        _currentTime = 0;
        _deltaTime = 0;
        _frameCounter = 0;
    }


    @Override
    public void run() {

        Canvas canvas;
        App app = App.getInstance();

        _deltaTime = System.currentTimeMillis();

        synchronized (this) {
            while (_isRunning) {

                _currentTime = System.currentTimeMillis();

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
                    _surface.update();
                    _surface.draw(canvas);

                    _frameCounter++;

                    if(_currentTime - _deltaTime >= 1000) {
                        _surface.fps = _frameCounter;
                        _surface.drawFPS();
                        _deltaTime = System.currentTimeMillis();
                        _frameCounter = 0;
                    }

                    if (app.isGameOver()) {
                        return;
                    }

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
    private long _currentTime;
    private long _deltaTime;
    private int _frameCounter;
}
