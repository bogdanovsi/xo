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

    private int size;


    private int playerX;
    private int playerY;

    public GameController() {
        map = new int[9];
        weight = 900;
        height = 900;
        player = true;

        playerX = 0;
        playerY = 0;
    }

    public GameController(float w, float h, int s) {
        this();
        weight = w;
        height = h;
        size = s;

        map = new int[size * size];
        setMap(size);
    }

    private void setMap(int size) {
        dxMap = new Point[size * size];

        stepX = weight / size;
        stepY = height / size;

        System.out.println("Steps: " + stepX + " " + stepY);

        dxMap[0] = new Point(stepX / 2f, stepY / 2f);

        dl = new Point(0, stepY / 2f).distanceTwoPoint(dxMap[0]);

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

        int p;
        if (player) {
            p = 1;
        } else {
            p = 2;
        }

        float dist;
        for (int i = 0; i < dxMap.length; i++) {
            dist = dxMap[i].distanceTwoPoint(point);

            if (dist == dl) {
                break;
            }

            if (dist < dl && map[i] == 0) {
                map[i] = p;

                Log.i("Distance: ", String.valueOf(dist));
                Log.i("Dl: ", String.valueOf(dl));

                checkMap(i);

                player = !player;
            }
        }

    }

    private void checkMap(int index) {
        switch (size) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                win3(index);
                break;
            case 4:
                break;
            default:
                break;

        }


    }

    private void win3(int index) {

        int pl = map[index];

        checkLine(index, pl, 1);
        System.out.println("Проверка горизонтали: " + (checkLine(index, pl, 1) + checkLine(index, pl, -1) - 1));
        checkLine(index, pl, size);
        System.out.println("Проверка вертикали: " + checkLine(index, pl, size));
        checkLine(index, pl, size - 1);
        System.out.println("Проверка правовверх: " + checkLine(index, pl, size - 1));
        checkLine(index, pl, size + 1);
        System.out.println("Проверка правовниз: " + checkLine(index, pl, size + 1));
        System.out.println("map: \n" +
                map[0] + " " + map[1] + " " + map[2] + "\n" +
                map[3] + " " + map[4] + " " + map[5] + "\n" +
                map[6] + " " + map[7] + " " + map[8]);
        //тогда мы находимся у левого края
//        if (index % size == 0) {
//        } else
//            //тогда мы находимся у правого края
//        {
//            if ((index + 1) % size == 0) {
//                checkLine(index, pl, -1);
//                System.out.println(checkLine(index, pl, -1));
//            } else {
//                //мы в области
//
//            }
//        }

    }

    private int checkLine(int index, int pl, int step) {
        int count = 0;

        if (index > 0 && index < size * size)
            if (map[index] == pl) {
                count++;
                count += checkLine(index + step, pl, step);
            }

        return count;
    }

    public void cleanMap() {
        map = new int[size * size];
        player = true;
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

    public int getSize() {
        return size;
    }

}

