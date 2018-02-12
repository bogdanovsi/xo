package com.freekerrr.xo;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by freekerrr on 11.02.2018.
 */

public class CanvasXO {

    private void init(AttributeSet attrs) {
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.MyCustomView);

        //Use a
        Log.i("test",a.getString(
                R.styleable.MyCustomView_android_text));
        Log.i("test",""+a.getColor(
                R.styleable.MyCustomView_android_textColor, Color.BLACK));
        Log.i("test",a.getString(
                R.styleable.MyCustomView_extraInformation));

        //Don't forget this
        a.recycle();
    }

}
