package com.capsulemedia.myapp.baskin31;

import android.content.Context;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;

import java.io.OptionalDataException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class GameFragment extends capsuleBaseFragment {

    Scanner sc = new Scanner(System.in);
    Random ran = new Random();

    int N=0, rand_N;
    int count = 0;

    String member[] = {"USER", "COM1", "COM2"};

    TextView gameLine;
    Button plusButton;
    Button doneButton;

    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
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
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        gameLine= view.findViewById(R.id.textView);
        plusButton = view.findViewById(R.id.plusButton);
        doneButton = view.findViewById(R.id.doneButton);
//        SetListener();
        mixRandom();
        game();
        //start
        if(mCapsuleBaseFragmentInterface!=null)
        {
            mCapsuleBaseFragmentInterface.onCreateEndNowReadyToStart();
        }
        return view;
    }

//    public void SetListener(){
//        View.OnClickListener Listener = new View.OnClickListener(){
//            public void onClick(View view){
//                switch (view.getId()){
//                    case R.id.plusButton:
//                        gameLine.setText("USER: "+ N);
//                    case R.id.doneButton:
//                        break;
//                }
//            }
//        };
//
//        plusButton.setOnClickListener(Listener);
//        doneButton.setOnClickListener(Listener);
//    }
    //멤버 섞는 함수
    // 멤버 섞는 함수
    public void mixRandom() {

        // 섞기
        Random rand = new Random();
        for(int x=0;x<20;x++){
            for (int i = member.length - 1; i > 0; i--) {
                int randIdx = rand.nextInt(i + 1);
                String temp = member[randIdx];
                member[randIdx] = member[i];
                member[i] = temp;
            }
        }
        // 출력( 확인 )
        gameLine.setText("게임 순서 : " + Arrays.toString(member)+"\n");
    }
    public void game() {
        int i, j;


        while (true) {
            for (i = 0; i < member.length; i++) {       // 멤버 체크
                if (member[i] == "USER") {              // 멤버가 USER이면
                    Log.i("멤버 순서 확인","user 순서");
                    plusButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            count++;
                            if (count > 3) {            //횟수가 3을 초과하면 Done 버튼 누르기 안내
                                Toast.makeText(getContext(), "Done 버튼을 눌러주세요!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            N++;
                            gameLine.setText(gameLine.getText()+"\n"+"USER : " + N);
                        }
                    });
                    doneButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (count == 0) {
                                Toast.makeText(getContext(), "+ 버튼을 눌러주세요!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            count = 0;                  // 횟수 초기화
                        }
                    });
                    if (N == 31) {                      //gameOver check
                        gameLine.setText(gameLine.getText()+"\n"+"YOU LOSE!!"+"\n");
                        return;
                    }
                }
                else{                                            // COM 턴
                    rand_N = ran.nextInt(3) + 1;          //1~3 랜덤 숫자
                    for(j=0;j<rand_N;j++){
                        Log.i("rand_N 확인","컴퓨터 랜덤 숫자 확인용"+rand_N);
                        N++;
                        gameLine.setText(gameLine.getText()+"\n"+member[i]+" : "+N);

                        if (N == 31){                           //gameOver check
                            gameLine.setText(gameLine.getText()+"\n"+"YOU WIN!!"+"\n");
                            return;
                        }
                    }
                }

            }
        }
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

//    public void setText(String s) {
//        if(gameLine!=null){
//            gameLine.setText(s);
//            Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
//        }
//    }

}
