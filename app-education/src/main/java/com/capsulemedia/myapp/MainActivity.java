package com.capsulemedia.myapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.capsulemedia.myapp.baskin31.GameFragment;
import com.capsulemedia.myapp.baskin31.MainFragment;
import com.capsulemedia.myapp.category.capsuleBaseActivity;
import com.capsulemedia.myapp.funcs.funcs;
import com.capsulemedia.myapp.pagePrc.pagePrc;

import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;


public class MainActivity extends capsuleBaseActivity {

    public static String number ; //모든 클래스에서 접근 가능

    //build : IMCAPSULE
    //build password :capsule2014!
    private  String TAG=getClass().getSimpleName();

    private static final int PERMISSION_ALL = 1024;
    private int PERMISSIONCOUNT=0;
    private boolean mPermissionChecked=false;

    public static MainActivity sMainActivity=null;

    private android.support.v4.app.Fragment mCurrentFragment=null;

    //프레그먼트로부터 데이터 받기

//    public Object getData(){
//        return number;
//    }
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean mayRequestPermissions() {
        Log.d(LOG_TAG,"mayRequestPermissions");

        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE

        };



        PERMISSIONCOUNT=PERMISSIONS.length;

        if(!hasPermissions(this, PERMISSIONS)){
            Log.d(LOG_TAG,"mayRequestPermissions request");
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);

            mPermissionChecked=false;
            new Thread(new Runnable() {
                @Override public void run() {

                    while(true)
                    {
                        if(mPermissionChecked)
                        {
                            mainActivityStartPrc();
                            break;
                        }

                    }
                }
            }).start();



        }
        else
        {
            Log.d(LOG_TAG,"mayRequestPermissions ok");
            mainActivityStartPrc();

        }


        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_ALL) {




            if (grantResults.length == PERMISSIONCOUNT)
            {
                int i=0;
                int ct=0;
                for(i=0;i<PERMISSIONCOUNT;i++)
                {

                    Log.d(LOG_TAG,"permission grantResults["+i+"]="+grantResults[i]);
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    {
                        ct++;
                    }
                }

                Log.d(LOG_TAG,"permission granted ct:"+ct);

                if(ct==PERMISSIONCOUNT)
                {
                    Log.d(LOG_TAG,"permission granted");

                    //ERROR SOME MOBILE
                    //mainActivityStartPrc();

                    mPermissionChecked=true;
                    return;
                }

            }

            Log.d(LOG_TAG,"permission denied PERMISSIONCOUNT:"+PERMISSIONCOUNT+":"+grantResults.length);
            finish();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set
        sMainActivity=this;
        pagePrc.currentContext=this;



        //set
        funcs.setScreenSize(this,false);




        mayRequestPermissions();




        //status bar color : this may make a crash!.
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(getApplication().getResources().getColor(R.color.colorBlack)); //status bar or the time bar at the top
        }
    }

//    public void setFragment(Fragment fragment)
//    {
//
//        mCurrentFragment=fragment;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.start_fragment, fragment);
//        fragmentTransaction.commit();
//
//    }

    public void setFragment(Fragment fragment)
    {

        mCurrentFragment=fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_main, fragment);
        fragmentTransaction.commit();

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
//        Intent intent = getIntent();
//        number = intent.getExtras().getString("number").toString();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_main, fragment).commit();
    }

//    public void addFragment(Fragment fragment)
//    {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container_main, fragment);
//        fragmentTransaction.commit();
//    }
//    public void removeFragment(Fragment fragment)
//    {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.remove(fragment);
//        fragmentTransaction.commit();
//    }


    private void mainActivityStartPrc()
    {
        pagePrc.baskin31Page();

        //pagePrc.setPuzzlePage(4,3);
    }

    @Override
    protected void onResume() {

        super.onResume();
        //Log.d(TAG,"onResume");
    }
    @Override
    protected void onStop() {

        super.onStop();
        //Log.d(TAG,"onStop");
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
       // Log.d(TAG,"onDestroy");
    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
