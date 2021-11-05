package com.koreait.board2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailActivity extends AppCompatActivity {

    public TextView tvIboard;
    public TextView tvTitle;
    public TextView tvCtnt;
    public TextView tvWriter;
    public TextView tvRdt;

    private int iboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        tvIboard = findViewById(R.id.tvIboard);
        tvTitle = findViewById(R.id.tvTitle);
        tvCtnt = findViewById(R.id.tvCtnt);
        tvWriter = findViewById(R.id.tvWriter);
        tvRdt = findViewById(R.id.tvRdt);

        Intent intent = getIntent();
        iboard = intent.getIntExtra("iboard", 0);
    }
    public void onStart() {
        super.onStart();
        getBoardDetail();
    }

    private void getBoardDetail(){
        Call<BoardVO> call = Network.getService().selBoard(iboard);
        call.enqueue(new Callback<BoardVO>() {
            @Override
            public void onResponse(Call<BoardVO> call, Response<BoardVO> response) {
                if(response.isSuccessful()){
                    BoardVO vo = response.body();
                    tvIboard.setText(String.valueOf(vo.getIboard()));
                    tvTitle.setText(vo.getTitle());
                    tvCtnt.setText(vo.getCtnt());
                    tvWriter.setText(vo.getWriter());
                    tvRdt.setText(vo.getRdt());


                } else {
                    Log.e("myLog", "통신 오류");
                }
            }

            @Override
            public void onFailure(Call<BoardVO> call, Throwable t) {
                Log.e("myLog", t.getMessage());
            }
        });
    }
}