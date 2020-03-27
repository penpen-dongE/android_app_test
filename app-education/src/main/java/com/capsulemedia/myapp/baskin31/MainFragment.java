package com.capsulemedia.myapp.baskin31;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capsulemedia.myapp.MainActivity;
import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;


public class MainFragment extends capsuleBaseFragment {

    MainActivity mActivity;
    private static String number;
    EditText numInput;

    Button startButton;
    private static final String ARG_number = "number";
    private String mParam1;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("ARG_number", number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_number);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Log.v("Test","Param1 : "+mParam1);
        numInput = view.findViewById(R.id.numInput);
        startButton = view.findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Log.i("Test","스타트 버튼 클릭 전! Value_check: " +number);

                mActivity.dataTransfer(number);

                // getActivity()로 MainActivity의 replaceFragment를 불러옴.
                ((MainActivity)getActivity()).replaceFragment(GameFragment.newInstance());
            }
        });

        //start
        if(mCapsuleBaseFragmentInterface!=null)
        {
            mCapsuleBaseFragmentInterface.onCreateEndNowReadyToStart();
        }

        return view;

    }

    public void startProcess() {

        numCheck();
        Log.d("Test","start_process 시작! Value_check: " +number);

    }

    public void numCheck(){

        numInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int count, int after) {
                if(str.toString().length() > 0)
                    if((Integer.parseInt(str.toString()) == 0) || (Integer.parseInt(str.toString()) > 5))
                {
                        numInput.setText(null);
                        Toast.makeText(getContext(),"1~5 사이의 숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                number = numInput.getText().toString();

                Log.d("Test","텍스트 입력 후! Value_check: " +number);

            }
        });
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity)context;
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
}
