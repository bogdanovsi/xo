package com.freekerrr.xo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by freekerrr on 06.03.2018.
 */

public class DialogCustomMapSize extends DialogFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dialog_custom_map_size, container, false);

        final EditText size = v.findViewById(R.id.size);
        final Button positiveButton = v.findViewById(R.id.ok);

        //it's work!!!
        this.getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int s = Integer.valueOf(size.getText().toString());

                    if (s > 0 && s < 50) {
                        ((StartMenu) getActivity()).createGame(s);
                        dismiss();
                    } else {
                        size.setText("");
                    }
                } catch (NumberFormatException e) {
                    dismiss();
                }

            }
        });

        return v;
    }
}
