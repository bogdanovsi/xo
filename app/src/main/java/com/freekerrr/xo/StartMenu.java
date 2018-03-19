package com.freekerrr.xo;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartMenu extends FragmentActivity {

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        animation = AnimationUtils.loadAnimation(this, R.anim.starfall);
//        starfall.startAnimation(animation);

        final Fragment startMenuFragment = new StartMenuFragment();

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, startMenuFragment);
        fragmentTransaction.commit();
    }

    public void singlePlayer(View view) {
        final Fragment singlePlayerMenuFragment = new SinglePlayerMenuFragment();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
        fragmentTransaction.replace(R.id.fragment, singlePlayerMenuFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void startGame(View view) {
        switch (view.getId()) {
            case R.id.x3:
                createGame(3);
                break;
            case R.id.x5:
                createGame(5);
                break;
            case R.id.x10:
                createGame(10);
                break;
            case R.id.x:
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
