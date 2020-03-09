package com.capsulemedia.myapp.puzzle;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.renderscript.Matrix2f;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;
import com.capsulemedia.myapp.funcs.funcs;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PuzzleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuzzleFragment extends capsuleBaseFragment {



    private String TAG="PuzzleFragment";

    private FrameLayout mGameLayout=null;

    public static int mMaxCol=0;
    public static int mMaxRow=0;
    public static int mHoleCol=0;
    public static int mHoleRow=0;
    public static int mGameState=0; //0:mix 1:play 2:end

    private Rect mGameBoardRect=new Rect();



    private ArrayList<CellClass> mCellList=new ArrayList<CellClass>();


    private int mBefDir=0;
    private int mMixCount=30;





    public PuzzleFragment() {
        // Required empty public constructor
    }

    public static PuzzleFragment newInstance() {
        PuzzleFragment fragment = new PuzzleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_puzzle, container, false);

        mGameLayout=(FrameLayout)view.findViewById(R.id.idGameBoard);


        //start
        if(mCapsuleBaseFragmentInterface!=null)
        {
            mCapsuleBaseFragmentInterface.onCreateEndNowReadyToStart();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    @Override
    public void onResume() {

        super.onResume();

    }
    @Override
    public void onStop() {

        super.onStop();

    }

    public void startProcess(int col,int row)
    {
        mMaxRow=row;
        mMaxCol=col;


        getGameBoardRect();

    }

    private void getGameBoardRect()
    {
        mGameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                mGameLayout.getGlobalVisibleRect(mGameBoardRect);
                mGameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                initCells();
                makeMix();
            }
        });
    }

    private void initCells()
    {

        Log.e(TAG,"mGameBoardRect:"+mGameBoardRect);

        //init all game variables
        mBefDir=0;
        mGameState=0;
        mMixCount=30;

        mGameLayout.removeAllViews();




        //set size
        float margin=(int)funcs.convertDpToPixel(4.0f,getContext());
        float baseLineWidth=0;
        float cellSize=0;
        baseLineWidth=mGameBoardRect.width()-margin*2;
        cellSize=baseLineWidth/mMaxRow;


        float basex=(mGameBoardRect.width()-cellSize*mMaxRow)/2;
        float basey=(mGameBoardRect.height()-cellSize*mMaxCol)/2;

        Log.e(TAG,"baseLineWidth:"+baseLineWidth);
        Log.e(TAG,"cellSize:"+cellSize);

        Log.e(TAG,"basex:"+basex);
        Log.e(TAG,"basey:"+basey);

        int c=0;
        int r=0;
        int data=1;
        for( c=0;c<mMaxCol;c++)
        {
            for( r=0;r<mMaxRow;r++)
            {

                if(c==mMaxCol-1 && r==mMaxRow-1) break;
                CellClass cell=new CellClass(getContext(),new CellAniInterface() {
                    @Override
                    public void animationEnd(int holeCol, int holeRow,int befDirection) {

                        mHoleCol=holeCol;
                        mHoleRow=holeRow;
                        mBefDir=befDirection;
                        Log.e(TAG,"--------------------hole:"+mHoleCol+":"+mHoleRow);
                        aniEndPrc();

                    }


                });
                cell.setLayoutParams(new ViewGroup.LayoutParams((int)cellSize, (int)cellSize));
                cell.setText(String.valueOf(data));
                cell.setX(basex+r*cellSize);
                cell.setY(basey+c*cellSize);

                cell.setCellSize(cellSize);
                cell.setMatrix(c,r);



                mGameLayout.addView(cell);

                mCellList.add(cell);
                data++;
            }
        }

        mHoleCol=mMaxCol-1;
        mHoleRow=mMaxRow-1;



    }
    private void aniEndPrc()
    {
        Log.e(TAG,"aniEndPrc:"+mGameState);
        switch(mGameState)
        {
            case 0:
                makeMix();
                break;
            case 1:
                checkGameEnd();
                break;
            case 2:
                break;
        }
    }
    private void makeMix()
    {
        Log.d(TAG,"mMixCount:"+mMixCount);
        mMixCount--;
        if(mMixCount==0)
        {
            mGameState=1;
            return;
        }
        // L 1 R 2 U 3 D 4
        int bucket[]=new int[4];
        int bucketCt=0;
        //left
        if(mHoleRow-1>=0 && mBefDir!=1)
        {
            bucket[bucketCt]=1;//LEFT
            bucketCt++;
        }
        if(mHoleRow+1<mMaxRow && mBefDir!=2)
        {
            bucket[bucketCt]=2;//RIGHT
            bucketCt++;
        }
        if(mHoleCol-1>=0 && mBefDir!=3)
        {
            bucket[bucketCt]=3;//up
            bucketCt++;
        }
        if(mHoleCol+1<mMaxCol && mBefDir!=4)
        {
            bucket[bucketCt]=4;
            bucketCt++;
        }

        //rand
        Random rnd=new Random();
        int index=rnd.nextInt(bucketCt);

        Log.d(TAG,"bucketCt:"+bucketCt);
        Log.d(TAG,"index:"+index);
        Log.d(TAG,"bucket[index]:"+bucket[index]);

        //move target
        switch(bucket[index])
        {
            case 1:
                getCell(mHoleCol,mHoleRow-1).moveToHole();
                break;
            case 2:
                getCell(mHoleCol,mHoleRow+1).moveToHole();
                break;
            case 3:
                getCell(mHoleCol-1,mHoleRow).moveToHole();
                break;
            case 4:
                getCell(mHoleCol+1,mHoleRow).moveToHole();
                break;
        }

    }

    private CellClass getCell(int col,int row)
    {

        for(int i=0;i<mCellList.size();i++)
        {
            CellClass cell=mCellList.get(i);
            if (cell.getCol()==col && cell.getRow()==row)
            {
                return cell;
            }
        }
        return null;
    }

    private void checkGameEnd()
    {

    }
}
