package com.freekerrr.xo;

/**
 * Created by freekerrr on 19.02.2018.
 */

public class Point {

    private float x;
    private float y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point plusXY(float x, float y) {
        float ex = this.x + x;
        float ey = this.y + y;
        return new Point(ex, ey);
    }

    public float distanceTwoPoint(Point p) {
        return (float) Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
