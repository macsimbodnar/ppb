package com.mazerfaker.pewpewboom.model;


public class App {

    private static App instance = null;


    private App() {
        _windowHeght = 0;
        _windowWidth = 0;
        _moveLeft = false;
        _moveRight = false;
        _fire = false;
    }


    public static App getInstance() {
        if(instance == null) {
            instance = new App();
        }
        return instance;
    }


    public void setWindowWidth(int width) {
        _windowWidth = width;
    }


    public void setWindowHeght(int height) {
        _windowHeght = height;
    }


    public void setWindowSize(int width, int height) {
        _windowHeght = height;
        _windowWidth = width;
    }


    public int getWindowWidth() {
        return _windowWidth;
    }


    public void moveLeft(boolean moveLeft) {
        _moveLeft = moveLeft;
    }


    public void  moveRight(boolean moveRight) {
        _moveRight = moveRight;
    }


    public boolean isMovingRight() {
        return _moveRight;
    }


    public boolean isMovingLeft() {
        return _moveLeft;
    }


    public int getWindowHeght() {
        return _windowHeght;
    }

    private int _windowWidth;
    private int _windowHeght;
    private boolean _moveLeft;
    private boolean _moveRight;
    private boolean _fire;
}
