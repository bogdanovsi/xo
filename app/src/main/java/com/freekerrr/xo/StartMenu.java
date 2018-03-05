package com.freekerrr.xo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void startGame(View view) {

        int size = 0;

        switch (view.getId()) {
            case R.id.bx3:
                size = 3;
                break;
            case R.id.bx5:
                size = 5;
                break;
            case R.id.bx10:
                size = 10;
                break;
            case R.id.bcustomSize:
                //entry
                break;

        }

        createGame(size);
    }

    private void createGame(int size) {

        Intent mainActivity = new Intent(StartMenu.this, MainActivity.class);
        mainActivity.putExtra("size", size);
        startActivity(mainActivity);

    }
}
