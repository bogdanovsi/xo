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
        return (float) Math.hypot(p.getX() - x, p.getY() - y);
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

    @Override
    public String toString() {
        return "x:" + x + " y:" + y;
    }

    public Point plusX(float x) {
        float ex = this.x + x;
        return new Point(ex, this.y);
    }

    public Point plusY(float y) {
        float ey = this.y + y;
        return new Point(this.x, ey);
    }
}
