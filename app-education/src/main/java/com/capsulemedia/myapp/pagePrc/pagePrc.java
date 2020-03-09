package com.capsulemedia.myapp.pagePrc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import com.capsulemedia.myapp.MainActivity;
import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.baskin31.MainFragment;
import com.capsulemedia.myapp.category.capsuleBaseFragment;
import com.capsulemedia.myapp.category.capsuleBaseFragmentInterface;
import com.capsulemedia.myapp.drawEx.MyDrawFragment;
import com.capsulemedia.myapp.localSaveData.localSavedData;
import com.capsulemedia.myapp.puzzle.PuzzleFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class pagePrc {


    public static ArrayList<Context> activityHistory=new ArrayList<Context>();
    public static Context currentContext=null;
    public static android.support.v4.app.Fragment currentFragment=null;


    public static android.support.v4.app.Fragment t_indicatorFragment=null;





    public static void popSimpleAlert(String msg,DialogInterface.OnClickListener listener)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(currentContext);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton("OK", listener);


//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


        TextView messageView = (TextView)alert11.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public static void popSimpleAlertYesNo(String msg,DialogInterface.OnClickListener oklistener,DialogInterface.OnClickListener nolistener)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(currentContext);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton("YES", oklistener);
        builder1.setNegativeButton("NO", nolistener);



        AlertDialog alert11 = builder1.create();
        alert11.show();


        TextView messageView = (TextView)alert11.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public static void popSimpleAlertYesNoWithInput(Context ct,String msg,EditText input,DialogInterface.OnClickListener oklistener,DialogInterface.OnClickListener nolistener)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ct);//Caution!!! if close and set this.....
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder1.setView(input);

        builder1.setPositiveButton("YES", oklistener);
        builder1.setNegativeButton("NO", nolistener);



        AlertDialog alert11 = builder1.create();
        alert11.show();


        TextView messageView = (TextView)alert11.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }


    public static void dismissSeveral(int ct)
    {

        if(activityHistory!=null)
        {

            ArrayList<Activity> tempList=new ArrayList<Activity>();
            for(int i=activityHistory.size()-1;i>=activityHistory.size()-ct;i--)
            {
                if(i<0 || i>=activityHistory.size()) continue;
                Activity t=(Activity)activityHistory.get(i);
                tempList.add(t);
            }

            for(int i=0;i<tempList.size();i++)
            {
                tempList.get(i).finish();
            }
        }
    }



    public static void finishApp()
    {

        if(activityHistory!=null)
        {

            ArrayList<Activity> tempList=new ArrayList<Activity>();
            for(int i=activityHistory.size()-1;i>=0;i--)
            {
                if(i<0 || i>=activityHistory.size()) continue;
                Activity t=(Activity)activityHistory.get(i);
                tempList.add(t);
            }

            for(int i=0;i<tempList.size();i++)
            {
                tempList.get(i).finish();
            }
        }
    }

    public static void dismiss()
    {

        if(currentContext!=null)
        {
            ((Activity)currentContext).finish();
        }
    }


    public static void setMyPage()
    {

        final MyDrawFragment fragment=MyDrawFragment.newInstance();
        MainActivity.sMainActivity.setFragment(fragment);

        ((capsuleBaseFragment)fragment).setOnCreateEndListner(new capsuleBaseFragmentInterface() {
            @Override
            public void onCreateEndNowReadyToStart() {
                ((MyDrawFragment)fragment).startProcess();
            }
        });
    }

    public static void baskinPage(){
        final MainFragment fragment=MainFragment.newInstance();
        MainActivity.sMainActivity.setFragment(fragment);

        ((capsuleBaseFragment)fragment).setOnCreateEndListner(new capsuleBaseFragmentInterface() {
            @Override
            public void onCreateEndNowReadyToStart() {
                ((MainFragment)fragment).startProcess();
            }
        });
    }

    public static void setPuzzlePage(final int col,final int row)
    {

        final PuzzleFragment fragment=PuzzleFragment.newInstance();
        MainActivity.sMainActivity.setFragment(fragment);

        ((capsuleBaseFragment)fragment).setOnCreateEndListner(new capsuleBaseFragmentInterface() {
            @Override
            public void onCreateEndNowReadyToStart() {
                ((PuzzleFragment)fragment).startProcess(col,row);
            }
        });
    }




    public static void popUserPage(final String userId)
    {
//        final myPageFragment fragment=myPageFragment.newInstance();
//        currentFragment=fragment;
//
//        Intent intent = new Intent(currentContext, containerActivity.class);
//        currentContext.startActivity(intent);
//
//        ((capsuleBaseFragment)fragment).setOnCreateEndListner(new capsuleBaseFragmentInterface() {
//            @Override
//            public void onCreateEndNowReadyToStart() {
//                ((myPageFragment)fragment).startProcess(userId);
//            }
//        });

    }



}
