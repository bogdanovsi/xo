package com.freekerrr.xo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartMenu extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void startGame(View view) {
        switch (view.getId()) {
            case R.id.bx3:
                createGame(3);
                break;
            case R.id.bx5:
                createGame(5);
                break;
            case R.id.bx10:
                createGame(10);
                break;
            case R.id.bcustomSize:
                showDialog();
                break;
        }
    }

    void showDialog() {
        DialogFragment newFragment = new DialogCustomMapSize();
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void createGame(int size) {
        Intent mainActivity = new Intent(StartMenu.this, MainActivity.class);
        mainActivity.putExtra("size", size);
        startActivity(mainActivity);
    }
}
