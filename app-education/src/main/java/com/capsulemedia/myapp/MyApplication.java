package com.capsulemedia.myapp;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


public class MyApplication extends MultiDexApplication {

    private static volatile MyApplication instance = null;

    public static MyApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.capsulemedia.imcapsule.MyApplication");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



}
