package com.capsulemedia.myapp.baskin31;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capsulemedia.myapp.R;
import com.capsulemedia.myapp.category.capsuleBaseFragment;

import java.util.Arrays;
import java.util.Random;


public class GameFragment extends capsuleBaseFragment {

    private GameLineAdapter adapter;
    private RecyclerView recyclerView;
    private Handler mHandler;
    private Runnable mRunnable;

    Random ran = new Random();

    private int N = 0;
    int rand_N;
    int count,mcount = 0;
    String nString;
    String memberN;     // 사용자가 입력한 멤버 숫자

    int memberNumber;

    String[] member;

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

        Bundle bundle = getArguments();
        if(bundle != null){
            memberN = bundle.getString("number");
            memberNumber = Integer.parseInt(memberN);
            member = new String[memberNumber+1];
        }
        Log.i("key 값","액티비티에서 번들로 넘겨받은 값 : " +memberN);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        layoutManager.setSmoothScrollbarEnabled(true);

        LinearLayoutManager layoutManager1 = new VariableScrollSpeed(getContext(), 4);
        recyclerView.setLayoutManager(layoutManager1);

        adapter = new GameLineAdapter();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getContext()," PostDelayed 테스트! ",Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        mHandler = new Handler();

        //리싸이클러뷰 지연시키기
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                recyclerView.scrollToPosition(adapter.getItemCount()-1);
//            }
//        },200);

        gameLine= view.findViewById(R.id.textView);
        plusButton = view.findViewById(R.id.plusButton);
        doneButton = view.findViewById(R.id.doneButton);

        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                count++;
                if (count > 3) {            //횟수가 3을 초과하면 Done 버튼 누르기 안내
                    Toast.makeText(getContext(), "Done 버튼을 눌러주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                N++;
                nString = Integer.toString(N);
                Log.i("nString 확인","USER 숫자 업데이트 확인용"+nString);
                adapter.addItem(new GameLine("USER", nString));
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(adapter.getItemCount()-1);
//                gameLine.setText(gameLine.getText()+"\n"+"USER : " + N);
                gameOver();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (count == 0) {
                    Toast.makeText(getContext(), "+ 버튼을 눌러주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameInterface.callbackF();
                    }
                },300);
                count = 0;                  // 횟수 초기화
            }
        });

        //start
        if(mCapsuleBaseFragmentInterface!=null)
        {
            mCapsuleBaseFragmentInterface.onCreateEndNowReadyToStart();
        }

        if(memberN != null){

            makeMember();           //멤버 만들기
            mixRandom();            //멤버 섞기
            game();                 //게임 시작

        }

        return view;
    }

    //멤버 만들기
    public void makeMember(){
        for(int i =0; i<memberNumber;i++){
            member[i] = "COM"+(i+1);
        }
        member[memberNumber]="USER";

        Log.i("member 배열에 값 넣기","멤버는!? : "+Arrays.toString(member));
    }
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
        Log.i("전체 순서 확인",Arrays.toString(member));
    }

    gameInterface gameInterface = new gameInterface() {
        @Override
        public void callbackF() {
            Log.i("콜백 확인", "콜백됨");
            mcount++;
            if(mcount == member.length){
                mcount = 0;
            }
            game();
        }
    };
    public void game() {
        if(N < 31){
            mTurn(mcount);
        } else{
            Toast.makeText(getContext(),"게임이 끝났습니다!! ",Toast.LENGTH_LONG).show();
        }
    }
    public void mTurn(int m) {

        if (member[m] == "USER") {              // 멤버가 USER이면
            Log.i("멤버 순서 확인", "user 순서");
            Toast toast = Toast.makeText(getContext(), "당신 턴 입니다!!", Toast.LENGTH_SHORT);
            toast.show();
        } else {                                            // COM 턴
            Log.i("멤버 순서 확인", "com 순서 : " + member[m]);
//            Log.i("mcount 숫자 확인", "mcount 숫자"+mcount);

            rand_N = ran.nextInt(3) + 1;          //1~3 랜덤 숫자
            for (int j = 0; j < rand_N; j++) {
//                Log.i("rand_N 확인","컴퓨터 랜덤 숫자 확인용"+rand_N);

                if(N<31){
                    N++;
                }else{
                    break;
                }
                nString = Integer.toString(N);

                //어댑터로 아이템 추가
                adapter.addItem(new GameLine(member[m], nString));

                //addItem 어댑터로 update
                recyclerView.setAdapter(adapter);

                //Focus 하단 고정
                recyclerView.scrollToPosition(adapter.getItemCount()-1);

//                adapter.notifyDataSetChanged();
//                gameLine.setText(gameLine.getText() + "\n" + member[m] + " : " + N);
                gameOver();

            }
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameInterface.callbackF();
                }
            },300);
        }
        return;
    }

    //gameOver check
    public void gameOver(){
        if (N >= 31) {
//            gameLine.setText(gameLine.getText() + "\n" + "GameOVer!!" + "\n");  //리스트뷰 줄바꾸면서 출력
            gameLine.setText("!! GAME-OVER !!");
            MyDialogFragment e = MyDialogFragment.getInstance();
            e.show(getFragmentManager(), MyDialogFragment.TAG_MY_DIALOG);


        }
//        else{
//            Toast.makeText(getContext(),"현재 숫자는 "+N,Toast.LENGTH_SHORT).show();
//        }
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

}
