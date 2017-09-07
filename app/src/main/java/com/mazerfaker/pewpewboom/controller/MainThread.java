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

        while(_isRunning) {
            if (!_pause) {
                canvas = null;

                // main game actions update and draw
                try {

                    canvas = _surfaceHolder.lockCanvas();
                    synchronized (_surfaceHolder) {
                        synchronized (App.getInstance()) {
                            _surface.update();
                            _surface.draw(canvas);

                            if (app.isGameOver()) {
                                return;
                            }
                        }

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


    public void setRunning(boolean running) {
        _isRunning = running;
    }


    public void pause(boolean pause) {
        _pause = pause;
    }


    private final SurfaceHolder _surfaceHolder;
    private Surface _surface;
    private boolean _pause;
    private boolean _isRunning;
}
