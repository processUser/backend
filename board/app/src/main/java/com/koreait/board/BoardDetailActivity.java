package com.koreait.board;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BoardDetailActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvCtnt;
    private TextView tvWriter;
    private TextView tvRdt;
    private BoardService service;
    private int iboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvCtnt = findViewById(R.id.tvCtnt);
        tvWriter = findViewById(R.id.tvWriter);
        tvRdt = findViewById(R.id.tvRdt);

        Retrofit rf = RetroFitObj.getInstance();
        service = rf.create(BoardService.class);

        // iboard값 전달 받기
        Intent intent = getIntent();
        iboard = intent.getIntExtra("iboard", 0);

    }
    @Override
    protected void onStart(){
        super.onStart();
        getBoardDetail();
    }
    private void getBoardDetail() {
        Call<BoardVO> call = service.selBoardDetail(iboard);
        call.enqueue(new Callback<BoardVO>() {
            @Override
            public void onResponse(Call<BoardVO> call, Response<BoardVO> response) {
                if(response.isSuccessful()){
                    BoardVO result= response.body();
                    tvTitle.setText(result.getTitle());
                    tvCtnt.setText(result.getCtnt());
                    tvWriter.setText(result.getWriter());
                    tvRdt.setText(result.getRdt());

                }else {
                    Log.e("myLog", "통신 오류");
                }
            }

            @Override
            public void onFailure(Call<BoardVO> call, Throwable t) {
                Log.e("myLog", "통신 완전 실패");
            }
        });
    }
    public void clkMod(View v) {
        // BoardModActivity 화면으로 이동 ( with iboard값도 넘긴다.)
        //Intent(주소값, 가고자하는곳)
        Intent intent = new Intent(this, BoardModActivity.class);
        intent.putExtra("iboard", iboard);
        startActivity(intent);
    }
    // 삭제 버튼 클릭시 작동 되는 부분.
    public void clkDel(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this)
                .setTitle("삭제").setMessage("정말 삭제하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("myLog","del-iboard : " + iboard);
                        //삭제처리 진행
                        Call<Void> call = service.delBoard(iboard);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Log.e("myLog", "del - 성공");
                                    finish();
                                } else {
                                    Log.e("myLog", "del - 통신 오류");

                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Log.e("myLog", "del - 통신 완전 실패");
                            }
                        });
                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });
//                .setNegativeButton("아니오", null); // 아무것도 안적을때는 null 가능.
        ad.create().show();
    }
}