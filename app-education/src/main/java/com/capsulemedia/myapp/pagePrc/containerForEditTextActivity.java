package com.capsulemedia.myapp.pagePrc;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseActivity;

public class containerForEditTextActivity extends capsuleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if(pagePrc.currentFragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_main, pagePrc.currentFragment);
            fragmentTransaction.commit();
        }


        //status bar color : this may make a crash!.
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(getApplication().getResources().getColor(R.color.colorBlack)); //status bar or the time bar at the top
        }
    }
}
