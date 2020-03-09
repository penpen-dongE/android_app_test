package com.capsulemedia.myapp.baskin31;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;

import java.util.Random;
import java.util.Scanner;


public class GameFragment extends capsuleBaseFragment {

    private TextView gameLine;

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

        TextView gameLine = view.findViewById(R.id.textView);

        Button button_plus = view.findViewById(R.id.button2);
        Button done = view.findViewById(R.id.button3);

    //    mixOrder();
        //start
        if(mCapsuleBaseFragmentInterface!=null)
        {
            mCapsuleBaseFragmentInterface.onCreateEndNowReadyToStart();
        }
        return view;
    }
    //멤버 섞는 함수
    public static void mixOrder(String[] args){
        //멤버
        int[] member = new int[5];
        //초기화
        for (int i=0; i<member.length; i++){
            member[i] = i;
        }
        //섞기
        int w = 0;
        int r;
        int temp;
        while(w < member.length){
            r = (int)(Math.random()*member.length); // 배열을 랜덤하게 뽑아낸다
            if(w!=r){                                // 뽑아낸 배열의 인덱스가 서로 다르다면
                temp = member[w];                    // swap으로 값을 바꾼다.
                member[w] = member[r];
                member[r] = temp;
                w++;
            }
        }
    }

    public static void game(String[] args) {

//		+ 배스킨라빈스31 게임
//		  - 컴퓨터와 사용자가 번갈아가면서 연속적으로 숫자를 말한다
//		  - 컴퓨터와 사용자는 한번의 기회에 연속된 숫자를 1개부터 3개까지
//		   말할 수 있다
//		  - 컴퓨터는 자기 순서에 랜덤한 갯수의 연속된 숫자를 말한다
//		  - 사용자는 1~3 입력을 통해 연속된 숫자를 말한다
//		  - 31을 말하는 사람이 진다

//		  * 추가 사항
//			 31에 가까워지면 컴퓨터는 랜덤이 아닌 자신이 이길수
//			있는 숫자까지만 말한다
//
//			ex. 사용자가 29까지 말했다면
//			  컴퓨터는 무조건 30까지만 말한다

        Scanner sc = new Scanner(System.in);
        Random ran = new Random();

        int num = 0;
        int count = 0;
        int com;

        while (true) {
            System.out.print("얼마나? : ");
            count = sc.nextInt();
            if (0 < count && count < 4) {
                for (int i = 0; i < count; i++) {
                    num = num + 1;
                    System.out.print(num + " ");
                    if (num == 31) {
                        System.out.println();
                        System.out.println("============[졌다..]=============");
                        return;
                    }
                }

                System.out.println();
                com = ran.nextInt(3) + 1;
                for (int i = 0; i < com; i++) {
                    if(num==28) {
                        com=2;
                    }
                    if(num==29) {
                        com=1;
                    }
                    num = num + 1;
                    System.out.print(num + " ");
                    if (num == 31) {
                        System.out.println();
                        System.out.println("=============[이겼다!]=============");
                        return;
                    }
                }
            }else {
                System.out.print("올바른 값을 입력하십시오.");
            }
            System.out.println();
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

    public void setText(String s) {
        if(gameLine!=null){
            gameLine.setText(s);
            Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
        }
    }
}
