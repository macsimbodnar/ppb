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
        _realFPS = 0;
    }


    @Override
    public void run() {

        Canvas canvas = null;

        long ticksPS = 1000 / Constants.FPS;
        long startTime = 0;
        long timeThisFrame = 0;
        long sleepTime = 0;
        App app = App.getInstance();


        while(_isRunning) {
            if (!_pause) {
                canvas = null;
                startTime = System.currentTimeMillis();

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


                // Pick render time and set sleep
                timeThisFrame = System.currentTimeMillis() - startTime;
                sleepTime = ticksPS - (timeThisFrame);

                try {

                    if (sleepTime > 0) {
                        _realFPS = 1000 / timeThisFrame;
                        sleep(sleepTime);
                    } else {
                        sleep(10);
                    }

                } catch (Exception e) {
                    Log.e(TAG, "Timer Error");
                    e.printStackTrace();
                }

                //Log.d(TAG, "CURRENT FRAME: " + _realFPS);
            }
        }
    }


    public long getFPS() {
        return _realFPS;
    }


    public void setRunning(boolean running) {
        _isRunning = running;
    }


    public void pause(boolean pause) {
        _pause = pause;
    }


    final private SurfaceHolder _surfaceHolder;
    private Surface _surface;
    private boolean _pause;
    private boolean _isRunning;
    private long _realFPS;
}
