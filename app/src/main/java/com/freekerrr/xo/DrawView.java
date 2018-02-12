package com.freekerrr.xo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

class DrawView extends View {

    private Paint p;
    private int weight;
    private int height;

    public DrawView(Context context) {
        super(context);
        p = new Paint();
        p.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        height = canvas.getHeight();
        weight = canvas.getWidth();
        drawTable(canvas, weight, height);
    }

    private void drawTable(Canvas canvas, int weight, int height){
        canvas.drawLine(weight*0.33f, 0f,weight*0.33f, height, p); // |
        canvas.drawLine(weight*0.66f, 0f,weight*0.66f, height, p); //  |
        canvas.drawLine(0f, height*0.33f, weight,height*0.33f, p); // --
        canvas.drawLine(0f, height*0.66f, weight,height*0.66f, p); // __
    }

}
