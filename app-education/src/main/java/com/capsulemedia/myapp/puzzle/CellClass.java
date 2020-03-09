package com.capsulemedia.myapp.puzzle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;


public class CellClass extends android.support.v7.widget.AppCompatButton {

    private String TAG="PuzzleFragment";
    private int mCol=0;
    private int mRow=0;
    private float mCellSize=0;

    private final long mAniDuration=100;

    private CellAniInterface mCellAniInterface=null;

    public CellClass(Context context) {
        super(context);
        init(null);
    }

    public CellClass(Context context,CellAniInterface ci) {
        super(context);
        init(ci);
    }

    public CellClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(null);
    }

    public CellClass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(null);
    }

    private void init(final CellAniInterface ci)
    {
        mCellAniInterface=ci;
        this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(PuzzleFragment.mGameState!=1) return;
                moveToHole();

            }
        });
    }
    public int getCol()
    {
        return mCol;
    }
    public int getRow()
    {
        return mRow;
    }

    public void moveToHole()
    {
        Log.e(TAG,"this:"+mCol+","+mRow);
        if(mCol-1==PuzzleFragment.mHoleCol && mRow==PuzzleFragment.mHoleRow)
        {
            moveUp(mCellAniInterface);
        }
        else if(mCol+1==PuzzleFragment.mHoleCol && mRow==PuzzleFragment.mHoleRow)
        {
            moveDown(mCellAniInterface);
        }
        else if(mRow-1==PuzzleFragment.mHoleRow && mCol==PuzzleFragment.mHoleCol)
        {
            moveLeft(mCellAniInterface);
        }
        else if(mRow+1==PuzzleFragment.mHoleRow && mCol==PuzzleFragment.mHoleCol)
        {
            moveRight(mCellAniInterface);
        }
    }

    public void setMatrix(int col,int row)
    {
        mCol=col;
        mRow=row;
    }

    public void setCellSize(float cellSize)
    {
        mCellSize=cellSize;

    }
    public void moveLeft(final CellAniInterface ci)
    {
       if(mRow-1<0) return;

        this.animate()
                .translationX(this.getX()-mCellSize)
                .setDuration(mAniDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mRow -=1;
                        ci.animationEnd(mCol,mRow+1,1);


                    }
                });

    }
    public void moveRight(final CellAniInterface ci)
    {
        if(mRow+1>=PuzzleFragment.mMaxRow) return;

        this.animate()
                .translationX(this.getX()+mCellSize)
                .setDuration(mAniDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mRow +=1;
                        ci.animationEnd(mCol,mRow-1,2);

                    }
                });

    }

    public void moveUp(final CellAniInterface ci)
    {
        if(mCol-1<0) return;

        this.animate()
                .translationY(this.getY()-mCellSize)
                .setDuration(mAniDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCol -=1;
                        ci.animationEnd(mCol+1,mRow,3);

                    }
                });

    }
    public void moveDown(final CellAniInterface ci)
    {
        if(mCol+1>=PuzzleFragment.mMaxCol) return;
        this.animate()
                .translationY(this.getY()+mCellSize)
                .setDuration(mAniDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCol +=1;
                        ci.animationEnd(mCol-1,mRow,4);

                    }
                });

    }
}
