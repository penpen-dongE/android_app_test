package com.capsulemedia.myapp.baskin31;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class InterfaceExamActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    GameFragment gamefragment;

    @Override
    public void onTextChange(String s) {
        if(gamefragment!=null){
            gamefragment.setText(s);
        }
    }
}
