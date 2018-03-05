package com.freekerrr.xo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    DrawView drawView;
    TextView playerX;
    TextView playerO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = findViewById(R.id.drawView);

        int size = getIntent().getIntExtra("size", 0);

        drawView.setSize(size);

        playerX = findViewById(R.id.playerX);
        playerO = findViewById(R.id.playerO);

        drawView.setTv(playerX, playerO);
    }

    public void clearCanvas(View view) {
        drawView.clearCanvas();
    }
}
