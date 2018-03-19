package com.freekerrr.xo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by freekerrr on 15.03.2018.
 */

public class DialogAdvertising extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dialog_advertising, container, false);

        final ImageButton positiveButton = v.findViewById(R.id.advertising);

        //it's work!!!
        this.getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Ad start", "$$$");

            }
        });

        return v;
    }
}
