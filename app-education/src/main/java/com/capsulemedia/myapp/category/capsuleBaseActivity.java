package com.capsulemedia.myapp.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.capsulemedia.myapp.pagePrc.pagePrc;

public class capsuleBaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pagePrc.currentContext=this;

        pagePrc.activityHistory.add(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
        pagePrc.currentContext=this;
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();


        pagePrc.activityHistory.remove(this);

    }
}
