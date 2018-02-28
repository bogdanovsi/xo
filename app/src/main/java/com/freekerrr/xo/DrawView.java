package com.freekerrr.xo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

class DrawView extends View {

    public float width;
    public float height;
    private Bitmap bitmap;
    private Canvas canvas;
    private Point[] dxMap;
    Context context;
    private Paint paint;

    private float stepX;
    private GameController gameController;
    private TextView tvplayerX;
    private TextView tvplayerO;

    public DrawView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        // and we set a new Paint with the desired attributes
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // your canvas will draw onto the defined Bitmap
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        width = w;
        height = h;

        gameController = new GameController(tvplayerX, tvplayerO, width, height, 5);

        dxMap = gameController.getDxMap();

        stepX = gameController.getStepX();
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        paint.setStrokeWidth(8f);
        if (gameController.getSize() > 15) {
            paint.setStrokeWidth(3f);
        }
        drawTable(width, height, gameController.getSize());
        paint.setStrokeWidth(4f);

        drawMap(gameController.getMap());
    }

    private void drawMap(int[] map) {
        for (int i = 0; i < map.length; i++) {

            if (map[i] != 0) {
                if (map[i] == 1) {
                    drawX(dxMap[i]);
                } else
                    drawO(dxMap[i]);
            }

        }
    }

    public void clearCanvas() {
        gameController.cleanMap();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i("actionDownCoordinates", "ex: " + ex + " ey: " + ey);
            gameController.oneStep(ex, ey);
            invalidate();
        }

        return true;
    }

    public void drawTable(float width, float height, int size) {

        float mulW = width / size;
        float mulH = height / size;

        for (float i = 1; i < size; i++) {
            canvas.drawLine(mulW * i, 0, mulW * i, height, paint);
            canvas.drawLine(0f, mulH * i, width, mulH * i, paint);
        }

//        canvas.drawLine(width * 0.333f, 0f, width * 0.333f, height, paint); // |
//        canvas.drawLine(width * 0.666f, 0f, width * 0.666f, height, paint); //  |
//        canvas.drawLine(0f, height * 0.333f, width, height * 0.333f, paint); // --
//        canvas.drawLine(0f, height * 0.666f, width, height * 0.666f, paint); // __
    }

    private void drawX(Point p) {
        float x1 = p.getX() - stepX * 0.4f;
        float x2 = p.getX() + stepX * 0.4f;
        float y1 = p.getY() - stepX * 0.4f;
        float y2 = p.getY() + stepX * 0.4f;

        canvas.drawLine(x1, y1, x2, y2, paint);
        canvas.drawLine(x2, y1, x1, y2, paint);
    }

    private void drawO(Point p) {
        float x1 = p.getX() - stepX * 0.4f;
        float x2 = p.getX() + stepX * 0.4f;
        float y1 = p.getY() - stepX * 0.4f;
        float y2 = p.getY() + stepX * 0.4f;

        canvas.drawOval(x1, y1, x2, y2, paint);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setTv(TextView tv1, TextView tv2) {
        tvplayerX = tv1;
        tvplayerO = tv2;
    }
}
