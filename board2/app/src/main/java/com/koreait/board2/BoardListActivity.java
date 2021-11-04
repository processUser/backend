package com.koreait.board2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
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
        setContentView(R.layout.activity_list_board);

        rvList = findViewById(R.id.rvList);
        adapter = new BoardListAdapter();
        rvList.setAdapter(adapter);

        getBoardList();
    }

    private void getBoardList(){
        BoardService service = Network.getService();

        Call<List<BoardVO>> call = service.selBoardList();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
                if(response.isSuccessful()){
                    List<BoardVO> result = response.body();
                    adapter.setList(result);
                    adapter.notifyDataSetChanged();

                } else {
                    Log.i("myLog", "list - 응답실패");
                }
            }

            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                Log.i("myLog", "list - 연결실패");

            }
        });
    }
}


// Adapter 생성 ===========>>>>>
class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.MyViewHolder>{
    private List<BoardVO> list ;

    public void setList(List<BoardVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_board,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BoardVO vo = list.get(position);
        holder.setItem(vo);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list == null? 0:list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIboard;
        private TextView tvTitle;
        private TextView tvWriter;
        private TextView tvRdt;

        public MyViewHolder(View v){
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