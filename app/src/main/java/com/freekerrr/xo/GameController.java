package com.freekerrr.xo;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by freekerrr on 21.02.2018.
 */

public class GameController {

    private int[] map;
    private float weight;
    private float height;
    private Point[] dxMap;
    private float dl;
    private float stepX;
    private float stepY;
    private boolean player;

    public GameController() {
        map = new int[9];
        weight = 900;
        height = 900;
        player = true;
    }

    public GameController(float w, float h) {
        this();
        weight = w;
        height = h;

        setMap(3);
    }

    private void setMap(int size) {
        dxMap = new Point[size * size];

        stepX = weight / size;
        stepY = height / size;

        System.out.println("Steps: " + stepX + " " + stepY);

        dxMap[0] = new Point(stepX / 2f, stepY / 2f);

        dl = new Point(0, stepY).distanceTwoPoint(dxMap[0]);

        for (int i = 1; i < dxMap.length; i++) {
            if (i % size == 0) {
                dxMap[i] = dxMap[i - size].plusY(stepY);
            } else {
                dxMap[i] = dxMap[i - 1].plusX(stepX);
            }
        }

        Log.i("dxMap", Arrays.toString(dxMap));
    }

    public void setWH(float weight, float height) {
        this.weight = weight;
        this.height = height;
        setMap(4);
    }

    public void setMap(int[] map) {
        this.map = map;
    }

    public void oneStep(float ex, float ey) {
        Point point = new Point(ex, ey);

        int p = 0;
        if (player) {
            p = 1;
        } else {
            p = 2;
        }

        float dist;
        for (int i = 0; i < dxMap.length; i++) {
            dist = dxMap[i].distanceTwoPoint(point);

            Log.i("Distance: ", String.valueOf(dist));
            Log.i("Dl: ", String.valueOf(dl));

            if (dist == dl) {
                break;
            }

            if (dist < dl) {
                map[i] = p;
            }
        }

        player = !player;
    }


    public int[] getMap() {
        return map;
    }

    public Point[] getDxMap() {
        return dxMap;
    }

    public float getStepX() {
        return stepX;
    }

    public float getStepY() {
        return stepY;
    }
}

