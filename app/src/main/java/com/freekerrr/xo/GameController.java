package com.freekerrr.xo;

import android.util.Log;
import android.widget.TextView;

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
    private TextView tvplayerX;
    private TextView tvplayerO;
    private int size;

    private int playerX;
    private int playerO;

    private int winScore;

    public GameController() {
        map = new int[9];
        weight = 900;
        height = 900;
        player = true;

        winScore = 3;
        playerX = 0;
        playerO = 0;
    }

    public GameController(TextView tv1, TextView tv2, float w, float h, int s) {
        this();
        weight = w;
        height = h;
        size = s;

        tvplayerX = tv1;
        tvplayerO = tv2;

        if (size == 1) {
            winScore = 1;
        } else if (size == 2) {
            winScore = 2;
        } else if (size == 4) {
            winScore = 4;
        } else if (size > 4) {
            winScore = 5;
        }

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

                checkWin(i);

                player = !player;
            }
        }

    }

    private void checkWin(int index) {

        int pl = map[index];
        System.out.println("index: " + index);

        int horizontal = 0;
        int rightDown = 0;
        int rightUp = 0;
        int vertical = 0;
//        тогда мы находимся у левого края
        if (index % size == 0) {
            horizontal = checkLine(index, pl, 1);
            rightDown = checkLine(index, pl, size + 1);
            rightUp = checkLine(index, pl, -size + 1);

            System.out.println("Проверка горизонтали: " + checkLine(index, pl, 1));
            System.out.println("Проверка право вниз: " + checkLine(index, pl, size + 1));
            System.out.println("Проверка право вверх: " + checkLine(index, pl, -size + 1));
        } else if ((index + 1) % size == 0) {
            //тогда мы находимся у правого края
            horizontal = checkLine(index, pl, -1);
            rightDown = checkLine(index, pl, size - 1);
            rightUp = checkLine(index, pl, -size - 1);

            System.out.println("Проверка горизонтали: " + checkLine(index, pl, -1));
            System.out.println("Проверка лево вниз: " + checkLine(index, pl, size - 1));
            System.out.println("Проверка лево вверх: " + checkLine(index, pl, -size - 1));
//                checkLine(index, pl, -1);
//                System.out.println(checkLine(index, pl, -1));
        } else {
            //мы в области
            horizontal = (checkLine(index, pl, 1) + checkLine(index, pl, -1) - 1);
            rightDown = (checkLine(index, pl, size + 1) + checkLine(index, pl, -(size + 1)) - 1);
            rightUp = (checkLine(index, pl, size - 1) + checkLine(index, pl, -(size - 1)) - 1);

            System.out.println("Проверка горизонтали: " + (checkLine(index, pl, 1) + checkLine(index, pl, -1) - 1));
            System.out.println("Проверка диагонали вправо вверх: " + (checkLine(index, pl, size - 1) + checkLine(index, pl, -(size - 1)) - 1));
            System.out.println("Проверка диагонали вправо вниз: " + (checkLine(index, pl, size + 1) + checkLine(index, pl, -(size + 1)) - 1));
        }

        vertical = (checkLine(index, pl, size) + checkLine(index, pl, -size) - 1);

        System.out.println("Проверка вертикали: " + (checkLine(index, pl, size) + checkLine(index, pl, -size) - 1));

        if (horizontal == winScore || rightDown == winScore || rightUp == winScore || vertical == winScore) {
            System.out.println(pl + " player win!!");
            if (pl == 1) {
                playerX++;
            } else {
                playerO++;
            }
            tvplayerX.setText("X: " + playerX);
            tvplayerO.setText("O: " + playerO);
        }
    }

    private int checkLine(int index, int pl, int step) {
        int count = 0;

        if (index >= 0 && index < size * size)
            if (map[index] == pl) {
                count++;
                count += checkLine(index + step, pl, step);
            }

        return count;
    }

    public void cleanMap() {
        map = new int[size * size];
        //  player = true;
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

    public int[] getPlayersScore() {
        return new int[]{playerX, playerO};
    }
}

