package com.capsulemedia.myapp.baskin31;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.capsulemedia.myapp.MainActivity;
import com.capsulemedia.myapp.R;

import java.util.Calendar;

public class MyDialogFragment extends DialogFragment {

    public static final String TAG_MY_DIALOG = "my_dialog";

    public  MyDialogFragment() {}
    public static MyDialogFragment getInstance(){
        MyDialogFragment e = new MyDialogFragment();
        return e;
    }

    // DialogFragment 사이즈 조정하기
    public void onResume(){
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
        super.onResume();
    }

    // DialogFragment 타이틀 제거
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Dialog mDialog = super.onCreateDialog(savedInstanceState);
        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return mDialog;
    }

    private void dismissDialog(){
        this.dismiss();
    }

    //앱 재시작
    private void restartSelf() {
        AlarmManager am = (AlarmManager)   getActivity().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 500, // one second
                PendingIntent.getActivity(getActivity(), 0, getActivity().getIntent(), PendingIntent.FLAG_ONE_SHOT
                        | PendingIntent.FLAG_CANCEL_CURRENT));
        Intent i = getActivity().getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.mydialog, container);
        Button returnButton = (Button) view.findViewById(R.id.reButton);
        Button finishButton = (Button) view.findViewById(R.id.finButton);

        // 시작 화면으로 가기
        returnButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                dismissDialog();
//                ((MainActivity)getActivity()).setFragment(MainFragment.newInstance());

                ((MainActivity)getActivity()).finish();
                restartSelf();


            }
        });

        //앱 종료
        finishButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ((MainActivity)getActivity()).moveTaskToBack(true);
                ((MainActivity)getActivity()).finish();
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });

        return view;
    }
}
