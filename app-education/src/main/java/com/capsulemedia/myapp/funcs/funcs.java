package com.capsulemedia.myapp.funcs;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class funcs {

    public static int WINDOWWIDTH=0;
    public static int WINDOWHEIGHT=0;

    public static int VIDEOWIDTH=360;
    public static int VIDEOHEIGHT=640;



    public static void setScreenSize(Context context, boolean bSowStatusbar) {
        int x, y, orientation = context.getResources().getConfiguration().orientation;
        WindowManager wm = ((WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point screenSize = new Point();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                display.getRealSize(screenSize);
                x = screenSize.x;
                y = screenSize.y;
            } else {
                display.getSize(screenSize);
                x = screenSize.x;
                y = screenSize.y;
            }
        } else {
            x = display.getWidth();
            y = display.getHeight();
        }

        WINDOWWIDTH = getWidth(x, y, orientation);
        WINDOWHEIGHT = getHeight(x, y, orientation);

        if(bSowStatusbar)
        {
            WINDOWHEIGHT=WINDOWHEIGHT-getStatusBarHeight(context);
        }



    }
    private static int getWidth(int x, int y, int orientation) {
        return orientation == Configuration.ORIENTATION_PORTRAIT ? x : y;
    }

    private static int getHeight(int x, int y, int orientation) {
        return orientation == Configuration.ORIENTATION_PORTRAIT ? y : x;
    }
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public static void changeLayoutSizeForSameAspectFill(SurfaceView resizeView, int width, int height, int scrW, int scrH)
    {
        int vw = width;
        int vh = height;

        float videoRatio=(float)vw/(float)vh;

        //Log.d(TAG,"screen size v:"+vw+":"+vh);





        int sh = scrH;
        int sw = scrW;
        float screenRatio=(float)sw/(float)sh;


        if(vw<=0 || vh<=0) return;

        if(screenRatio>videoRatio)
        {
            //cut top bottom
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                float aw=vw*sh/vh;
                float ah=sh;
                float adj=sw/aw;
                resizeView.setScaleX(1.0f);
                resizeView.setScaleY(adj);
            }
            else
            {
                resizeView.getLayoutParams().width=sw;
                resizeView.getLayoutParams().height=sw*vh/vw;
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//                {
//                    resizeView.setY((sh-resizeView.getLayoutParams().height)/2.0f);
//                }
                resizeView.requestLayout();
            }

            //Log.d(TAG,"screen size 0:"+sw+":"+sh+"r:"+screenRatio+" vr:"+videoRatio);
        }
        else// if(screenRatio<videoRatio)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                float aw=sw;
                float ah=vh*sw/vw;
                float adj=sh/ah;
                resizeView.setScaleX(adj);
                resizeView.setScaleY(1.0f);
            }
            else
            {
                resizeView.getLayoutParams().width=sh*vw/vh;
                resizeView.getLayoutParams().height=sh;

//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//                {
//                    resizeView.setX((sw - resizeView.getLayoutParams().width) / 2.0f);
//                }

                resizeView.requestLayout();
            }


            //Log.d(TAG, "screen size 1:" + sw + ":" + sh + " r:" + screenRatio + " vr:" + videoRatio);
        }

    }

    public static void changeLayoutSizeForSameAspectFit(SurfaceView resizeView, int width, int height,int scrW,int scrH)
    {
        int vw = width;
        int vh = height;
        float videoRatio=(float)vw/(float)vh;
        int sh = scrH;
        int sw = scrW;
        float screenRatio=(float)sw/(float)sh;

        if(vw<=0 || vh<=0) return;

        if(screenRatio<videoRatio)
        {
            //cut top bottom
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                float aw=vw*sh/vh;
                float ah=sh;
                float adj=sw/aw;
                resizeView.setScaleX(1.0f);
                resizeView.setScaleY(adj);
            }
            else
            {
                resizeView.getLayoutParams().width=sw;
                resizeView.getLayoutParams().height=sw*vh/vw;
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//                {
//                    resizeView.setY((sh-resizeView.getLayoutParams().height)/2.0f);
//                }
                resizeView.requestLayout();
            }

            //Log.d(TAG,"screen size 0:"+sw+":"+sh+"r:"+screenRatio+" vr:"+videoRatio);
        }
        else //if(screenRatio>videoRatio)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                float aw=sw;
                float ah=vh*sw/vw;
                float adj=sh/ah;
                resizeView.setScaleX(adj);
                resizeView.setScaleY(1.0f);
            }
            else
            {
                resizeView.getLayoutParams().width=sh*vw/vh;
                resizeView.getLayoutParams().height=sh;

//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//                {
//                    resizeView.setX((sw - resizeView.getLayoutParams().width) / 2.0f);
//                }

                resizeView.requestLayout();
            }


            //Log.d(TAG, "screen size 1:" + sw + ":" + sh + " r:" + screenRatio + " vr:" + videoRatio);
        }

    }






    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
    }

    public static void downKeyboard(Context context, EditText editText) {

        InputMethodManager mInputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);

        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }



    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelToDp(float px,Context context)
    {
        return (float) (px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    //내부 저장소에 디렉토리 만들기
    public static File makeInternalDirectory(Context context,String dirName)
    {
        File sd_path =context.getFilesDir();
        String dest_dir_path = sd_path.getPath() + "/"+dirName;

        File dir = new File(dest_dir_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }


    //외부저장소에 디렉토리 만들
    public static File makeExternalDirectory(Context context,String dirName)
    {
        String sd_path =Environment.getExternalStorageDirectory().getPath();
        String dest_dir_path = sd_path + "/"+dirName;

        File dir = new File(dest_dir_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    //파일 삭제
    public static void removeFileWithPath(String path)
    {
        File tempFile = new File(path);
        if(tempFile.exists())
            tempFile.delete();
    }

    //디렉토리 삭기
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }




    //파일에 텍스트 쓰
    public static void writeFile(File file,String data)
    {

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);

            try {
                stream.write(data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stream.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //파일의 텍스트 가져오
    public static String readFile(File file)
    {
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            try {
                in.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        String contents = new String(bytes);


        return  contents;
    }

}
