package com.freekerrr.xo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

class DrawView extends View {

    public int width;
    public int height;
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    Context context;
    private Paint paint;
    private float x, y;
    private XOManager xoManager;


    public DrawView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        xoManager = new XOManager();
        // we set a new Path
        path = new Path();

        // and we set a new Paint with the desired attributes
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your canvas will draw onto the defined Bitmap
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        int weight = canvas.getWidth();
        int height = canvas.getHeight();

        super.onDraw(canvas);
        // draw the path with the paint on the canvas when onDraw
        canvas.drawPath(path, paint);

        paint.setStrokeWidth(6f);
        drawTable(weight, height);
        paint.setStrokeWidth(4f);
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            xoManager.oneStep(ex, ey);

        }
        return true;
    }

    public void drawTable(int weight, int height) {
        canvas.drawLine(weight * 0.33f, 0f, weight * 0.33f, height, paint); // |
        canvas.drawLine(weight * 0.66f, 0f, weight * 0.66f, height, paint); //  |
        canvas.drawLine(0f, height * 0.33f, weight, height * 0.33f, paint); // --
        canvas.drawLine(0f, height * 0.66f, weight, height * 0.66f, paint); // __
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Created by freekerrr on 19.02.2018.
     */

    public static class XOManager {

        private int[] map;
        private float weight;
        private float height;
        private Point[] dxMap;
        private float dl;
        private float stepX;
        private float stepY;
        private boolean player;

        public XOManager() {
            map = new int[9];
        }

        public XOManager(int w, int h) {
            this();
            weight = w;
            height = h;

            setMap();
        }

        private void setMap() {
            dxMap = new Point[9];

            stepX = weight / 0.33f;
            stepY = height / 0.33f;

            dxMap[0] = new Point(stepX / 2f, stepY / 2f);

            dl = new Point().distanceTwoPoint(dxMap[0]);

            for (int i = 1; i < 9; i++) {
                dxMap[i] = dxMap[i - 1].plusXY(stepX, stepY);
            }
        }

        public void setWH(float weight, float height) {
            this.weight = weight;
            this.height = height;
            setMap();
        }

        public void oneStep(int ex, int ey) {
            Point point = new Point(ex, ey);

            float dist;
            for (Point p :
                    dxMap) {
                dist = p.distanceTwoPoint(point);
                if (dist == dl)
                    return;
                if (dist < dl) {
                    drawStep(p);
                    player = !player;

                }

            }
        }

        private void drawStep(Point p) {
            if (player) {
                drawX(p);
            } else {
                drawO(p);
            }
        }

        private void drawX(Point p) {

        }

        private void drawO(Point p) {

        }

    }
}
