package com.capsulemedia.myapp.baskin31;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.capsulemedia.myapp.MainActivity;
import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;


public class MainFragment extends capsuleBaseFragment {

    //private Object number;
    EditText numInput;

    Button startButton;
    private static String number;

//    private FragmentListener fragmentListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
//        Bundle args = new Bundle();
//        args.putString(number, number);
//        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        numInput = view.findViewById(R.id.numInput);
        startButton = view.findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Log.w("Test","스타트 버튼 클릭 전! Value_check: " +number);
                // int num = Integer.parseInt(number);
//                fragmentListener.onTextChange(number);
                // mainFragment > mainActivity 로 데이터 보내기

                // getActivity()로 MainActivity의 replaceFragment를 불러옴.
               // ((MainActivity)getActivity()).replaceAndDataFragment(MainFragment.newInstance(number));
                ((MainActivity)getActivity()).replaceFragment(GameFragment.newInstance());
                // 새로 불러올 Fragment의 Instance를 Main으로 전달
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
//        num = numInput.getText().toString();
        Log.d("Test","start_process 시작! Value_check: " +number);
//        gameStart();

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
//                if (fragmentListener!=null){
//                    fragmentListener.onTextChange(editable.toString());
//                }
            }
        });

    }

//    public interface FragmentListener {
//        // TODO: Update argument type and name
//        void onTextChange(String s);
//    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(getActivity() instanceof FragmentListener){
//            this.fragmentListener = (FragmentListener) getActivity();
//        }
    }

//    public void gameStart(){
//
//        startButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//
//                    // getActivity()로 MainActivity의 replaceFragment를 불러옵니다.
//                ((MainActivity)getActivity()).replaceFragment(GameFragment.newInstance());
//                // 새로 불러올 Fragment의 Instance를 Main으로 전달
//            }
//        });
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if(getActivity() != null && getActivity() instanceof MainActivity){
//            number = ((MainActivity)getActivity()).getData();
//        }

//        if(context instanceof FragmentListener){
//            fragmentListener = (FragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + "must implement FragmentListener");
//        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
//        fragmentListener = null;

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
