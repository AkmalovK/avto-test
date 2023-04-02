package com.example.autotest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultRecycleViewAdapter extends RecyclerView.Adapter<ResultRecycleViewAdapter.RecyclerViewHolder>{

    private ArrayList<ResultModel> array;
    private Context mcontext;

    public ResultRecycleViewAdapter(ArrayList<ResultModel> recyclerDataArrayList, Context mcontext) {
        this.array = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_main, parent, false);
        return new ResultRecycleViewAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ResultModel recyclerData = array.get(position);
        holder.answer.setText(recyclerData.getAnswer());
        holder.question.setText(recyclerData.getQuestion());
        holder.image.setImageResource(recyclerData.getImage());
        if (recyclerData.getIsTrue() == 1) {
            holder.answer.setBackgroundColor(Color.GREEN);
        } else {
            holder.answer.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView question;
        private TextView answer;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.result_image);
            question = itemView.findViewById(R.id.result_question);
            answer = itemView.findViewById(R.id.result_answer);
        }
    }
}
