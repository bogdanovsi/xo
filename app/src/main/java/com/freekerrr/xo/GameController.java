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
    private DrawView drawView;

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

                checkWin(i);

                player = !player;

                break;
            }
        }

    }

    private void checkWin(int index) {
        int pl = map[index];
        System.out.println("index: " + index);

        WinLine horizontal = null;
        WinLine rightDown = null;
        WinLine rightUp = null;
        WinLine vertical = null;
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
            horizontal = checkLine(index, pl, 1).plus(checkLine(index, pl, -1));
            horizontal.count -= 1;
            rightDown = checkLine(index, pl, size + 1).plus(checkLine(index, pl, -(size + 1)));
            rightDown.count -= 1;
            rightUp = checkLine(index, pl, size - 1).plus(checkLine(index, pl, -(size - 1)));
            rightUp.count -= 1;

//            System.out.println("Проверка горизонтали: " + (checkLine(index, pl, 1) + checkLine(index, pl, -1) - 1));
//            System.out.println("Проверка диагонали вправо вверх: " + (checkLine(index, pl, size - 1) + checkLine(index, pl, -(size - 1)) - 1));
//            System.out.println("Проверка диагонали вправо вниз: " + (checkLine(index, pl, size + 1) + checkLine(index, pl, -(size + 1)) - 1));
        }

        vertical = checkLine(index, pl, size).plus(checkLine(index, pl, -size));
        vertical.count -= 1;

        // System.out.println("Проверка вертикали: " + (checkLine(index, pl, size) + checkLine(index, pl, -size) - 1));

        if (horizontal.count == winScore) {
            drawView.setWinLine(horizontal);
            increaseScope(pl);
        } else if (rightDown.count == winScore) {
            drawView.setWinLine(rightDown);
            increaseScope(pl);
        } else if (rightUp.count == winScore) {
            drawView.setWinLine(rightUp);
            increaseScope(pl);
        } else if (vertical.count == winScore) {
            drawView.setWinLine(vertical);
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

    private WinLine checkLine(int index, int pl, int step) {
        WinLine winLine = new WinLine();

        if (index >= 0 && index < size * size)
            if (map[index] == pl) {
                winLine.count++;
                if (index > winLine.indexEnd) {
                    winLine.indexEnd = index;
                } else if (index < winLine.indexStart || winLine.indexStart == 0) {
                    winLine.indexStart = index;
                }
                winLine.plus(checkLine(index + step, pl, step));
            }

        return winLine;
    }

    public void cleanMap() {
        setMap(new int[size * size]);
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

    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }
}

