package com.capsulemedia.myapp.baskin31;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capsulemedia.myapp.R;

public class Layout1 extends LinearLayout {

    TextView textView2;
    TextView textView3;

    public Layout1(Context context) {
        super(context);

        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1, this, true);

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
    }

    public void setName(String name){
        textView2.setText(name);
    }

    public void setResult(String result){
        textView3.setText(result);
    }
}

