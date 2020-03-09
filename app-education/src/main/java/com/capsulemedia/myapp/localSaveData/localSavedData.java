package com.capsulemedia.myapp.localSaveData;

import android.content.SharedPreferences;
import android.util.Log;

import com.capsulemedia.myapp.pagePrc.pagePrc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class localSavedData
{

    public static boolean notShowAlaramPublicNotice(int noticeid)
    {

        SharedPreferences PREF = pagePrc.currentContext.getSharedPreferences("DEFAULTS", MODE_PRIVATE);
        boolean bIsAble=PREF.getBoolean("LD_NOT_NEW_SHOW_AL_PN_"+String.valueOf(noticeid),false);

        return bIsAble;
    }
    public static void notShowAlaramPublicNoticeForever(int noticeid)
    {


        SharedPreferences PREF = pagePrc.currentContext.getSharedPreferences("DEFAULTS", MODE_PRIVATE);
        SharedPreferences.Editor editor = PREF.edit();
        editor.putBoolean("LD_NOT_NEW_SHOW_AL_PN_"+String.valueOf(noticeid), true);
        editor.commit();
    }
}
