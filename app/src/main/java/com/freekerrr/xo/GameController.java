package com.freekerrr.xo;

import android.widget.TextView;

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
    private DrawView drawView;

    private int playerX;
    private int playerO;

    private int winScore;
    private int countSteps;

    public GameController() {
        map = new int[9];
        weight = 900;
        height = 900;
        player = true;


        countSteps = 0;
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
        setDxMap(size);
    }

    private void setDxMap(int size) {
        dxMap = new Point[size * size];

        stepX = weight / size;
        stepY = height / size;

        dxMap[0] = new Point(stepX / 2f, stepY / 2f);

        dl = new Point(0, stepY / 2f).distanceTwoPoint(dxMap[0]);

        for (int i = 1; i < dxMap.length; i++) {
            if (i % size == 0) {
                dxMap[i] = dxMap[i - size].plusY(stepY);
            } else {
                dxMap[i] = dxMap[i - 1].plusX(stepX);
            }
        }

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
                countSteps++;

                map[i] = p;

                checkWin(i);

                player = !player;

                if (countSteps == size * size) {
                    cleanMap();
                }
                break;
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
            horizontal = checkVector(index, pl, 1);
            rightDown = checkVector(index, pl, size + 1);
            rightUp = checkVector(index, pl, -size + 1);

            System.out.println("Проверка горизонтали: " + checkVector(index, pl, 1));
            System.out.println("Проверка право вниз: " + checkVector(index, pl, size + 1));
            System.out.println("Проверка право вверх: " + checkVector(index, pl, -size + 1));
        } else if ((index + 1) % size == 0) {
            //тогда мы находимся у правого края
            horizontal = checkVector(index, pl, -1);
            rightDown = checkVector(index, pl, size - 1);
            rightUp = checkVector(index, pl, -size - 1);

            System.out.println("Проверка горизонтали: " + checkVector(index, pl, -1));
            System.out.println("Проверка лево вниз: " + checkVector(index, pl, size - 1));
            System.out.println("Проверка лево вверх: " + checkVector(index, pl, -size - 1));

        } else {
            //мы в области
            horizontal = (checkVector(index, pl, 1) + checkVector(index, pl, -1) - 1);
            rightDown = (checkVector(index, pl, size + 1) + checkVector(index, pl, -(size + 1)) - 1);
            rightUp = (checkVector(index, pl, size - 1) + checkVector(index, pl, -(size - 1)) - 1);

            System.out.println("Проверка горизонтали: " + (checkVector(index, pl, 1) + checkVector(index, pl, -1) - 1));
            System.out.println("Проверка диагонали вправо вверх: " + (checkVector(index, pl, size - 1) + checkVector(index, pl, -(size - 1)) - 1));
            System.out.println("Проверка диагонали вправо вниз: " + (checkVector(index, pl, size + 1) + checkVector(index, pl, -(size + 1)) - 1));
        }

        vertical = (checkVector(index, pl, size) + checkVector(index, pl, -size) - 1);

        System.out.println("Проверка вертикали: " + (checkVector(index, pl, size) + checkVector(index, pl, -size) - 1));

        if (horizontal == winScore || vertical == winScore || rightDown == winScore || rightUp == winScore) {
            increaseScope(pl);
        }
    }

    private void increaseScope(int pl) {
        if (pl == 1) {
            playerX++;
        } else {
            playerO++;
        }

        tvplayerX.setText("X: " + playerX);
        tvplayerO.setText("O: " + playerO);

        cleanMap();
    }

    private int checkVector(int index, int pl, int step) {
        int count = 0;

        if (indexOfMap(index) && map[index] == pl) {
            count++;

            int newIndex = index + step;
            if (indexOfMap(newIndex) && map[newIndex] == pl
                    && (newIndex % size == 2 || newIndex % size == 0)
                    && (step == 2 || step == -2)) {

                count++;

                return count;
            }

            count += checkVector(index + step, pl, step);
        }

        return count;
    }

    private boolean indexOfMap(int index) {
        if (index >= 0 && index < size * size) {
            return true;
        } else {
            return false;
        }
    }

    public void cleanMap() {
        map = new int[size * size];
        countSteps = 0;
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

    public void setMap(int[] map) {
        this.map = map;
    }

    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }
}

