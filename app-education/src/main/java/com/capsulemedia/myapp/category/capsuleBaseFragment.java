package com.capsulemedia.myapp.category;

import android.content.Context;
import android.support.v4.app.Fragment;

public class capsuleBaseFragment extends Fragment {

    protected capsuleBaseFragmentInterface mCapsuleBaseFragmentInterface;

    protected boolean mBFragmentAlived=false;


    public void setOnCreateEndListner(capsuleBaseFragmentInterface itfc)
    {
        mCapsuleBaseFragmentInterface=itfc;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mBFragmentAlived=true;
    }

    @Override
    public void onDetach() {
        super.onDetach();


        mBFragmentAlived=false;
    }

}
