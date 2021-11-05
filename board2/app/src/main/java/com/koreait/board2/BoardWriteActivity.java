package com.koreait.board2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWriteActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etCtnt;
    private EditText etWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        etTitle = findViewById(R.id.etTitle);
        etCtnt = findViewById(R.id.etCtnt);
        etWriter = findViewById(R.id.etWriter);
    }

    public void clkSave(View v){
        String title = etTitle.getText().toString();
        String ctnt = etCtnt.getText().toString();
        String writer = etWriter.getText().toString();

        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(writer);

        BoardService service = Network.getService();
        Call<Map<String, Integer>> call = service.insBoard(vo); // Map 는 순서 중요하지 않다. key 와 value 값으로..
        call.enqueue(new Callback<Map<String, Integer>>() {
            @Override
            public void onResponse(Call<Map<String, Integer>> call, Response<Map<String, Integer>> response) {
                if(response.isSuccessful()){
                    Map<String, Integer> map = response.body();
                    int result = map.get("result");

                    switch(result) {
                        case 1:
                            finish();
                            break;
                        default:
                            InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                            Snackbar.make(v, R.string.msg_fail, Snackbar.LENGTH_SHORT).show();
                            break;
                    }

                } else {
                    Log.e("myLog", "통신 오류");

                }
            }

            @Override
            public void onFailure(Call<Map<String, Integer>> call, Throwable t) {
                Log.e("myLog", t.getMessage());
            }
        });

    }
    public void clkCancel(View v){
        finish();
    }
}