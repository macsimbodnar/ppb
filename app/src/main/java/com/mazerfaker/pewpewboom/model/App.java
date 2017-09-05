package com.mazerfaker.pewpewboom.model;


public class App {
    private static App instance = null;

    protected App() {}

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


    public int getWindowHeght() {
        return _windowHeght;
    }

    private int _windowWidth;
    private int _windowHeght;
}
