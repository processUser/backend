package com.koreait.board;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardListActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private BoardListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
        rvList = findViewById(R.id.rvList);
        adapter = new BoardListAdapter();
        rvList.setAdapter(adapter);
        getBoardList();
        //역순으로 출력 되게 하는 것 ---->
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rvList.setLayoutManager(mLayoutManager);
        //<----
    }
    //글쓰기 Activity로 이동
    public void clkWrite(View v){
        Intent intent = new Intent(this, BoardWriteActivity.class);
        startActivity(intent);
    }

    private void getBoardList(){
        Retrofit retrofit = RetroFitObj.getInstance();
        BoradService service = retrofit.create(BoradService.class);

        Call<List<BoardVO>> call = service.selBoardList();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
                if(response.isSuccessful()){
                    List<BoardVO> result = response.body();
                    adapter.setList(result);
                    adapter.notifyDataSetChanged();

                } else {
                    Log.e("myLog", "통신오류" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                Log.e("myLog", "연결 자체가 실패" );
            }
        });
    }
}
class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.MyViewHolder> {
    private List<BoardVO> list;
    public void setList(List<BoardVO> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_board, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BoardVO vo = list.get(position);
        holder.setItem(vo);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIboard;
        private TextView tvTitle;
        private TextView tvWriter;
        private TextView tvRdt;

        public MyViewHolder(View v) {
            super(v);
            tvIboard = v.findViewById(R.id.tvIboard);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvWriter = v.findViewById(R.id.tvWriter);
            tvRdt = v.findViewById(R.id.tvRdt);
        }
        public void setItem(BoardVO param){
            tvIboard.setText(String.valueOf(param.getIboard()));
            tvTitle.setText(param.getTitle());
            tvWriter.setText(param.getWriter());
            tvRdt.setText(param.getRdt());
        }
    }
}