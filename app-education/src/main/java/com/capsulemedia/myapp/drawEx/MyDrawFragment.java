package com.capsulemedia.myapp.drawEx;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;
import com.capsulemedia.myapp.funcs.funcs;


public class MyDrawFragment extends capsuleBaseFragment {

    private EditText mEditTextView=null;
    private MyView mMyVIew=null;

    public MyDrawFragment() {
        // Required empty public constructor
    }

    public static MyDrawFragment newInstance() {
        MyDrawFragment fragment = new MyDrawFragment();

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
        View view= inflater.inflate(R.layout.fragment_my_draw, container, false);

        mMyVIew=(MyView)view.findViewById(R.id.idMyVIew);
        mEditTextView=(EditText)view.findViewById(R.id.idExitText);


        //엔터 키를 누르는 것 감지.
        mEditTextView.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                (event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                        {
                            if (event == null || !event.isShiftPressed()) {

                                //이 부분이 엔터키만 눌렸단느 것입니다. 다른 부분 신경쓰지 마세요
                                // the user is done typing.
                                funcs.downKeyboard(getContext(),mEditTextView);
                                //execute!
                                execCommand();


                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );
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

    public void startProcess()
    {

    }

    private void execCommand()
    {
        String cmd=mEditTextView.getText().toString();

        if(cmd.equals("draw circle"))
        {
            mMyVIew.drawCircle();
        }
        else if(cmd.equals("draw rect"))
        {
            mMyVIew.drawRect();
        }


        mEditTextView.setText("");
    }
}
