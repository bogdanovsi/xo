package com.freekerrr.xo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private DrawView drawView;
    private TextView playerX;
    private TextView playerO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = findViewById(R.id.drawView);
        playerX = findViewById(R.id.playerX);
        playerO = findViewById(R.id.playerO);
        drawView.setTextViews(playerX, playerO);

        //get size map from the previous activity
        int size = getIntent().getIntExtra("size", 0);
        drawView.setSize(size);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void dialogAdvertising(View view){
        DialogFragment newFragment = new DialogAdvertising();
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void clearCanvas(View view) {
        drawView.clearCanvas();
    }

    public void back(View view) {
        finish();
    }
}
