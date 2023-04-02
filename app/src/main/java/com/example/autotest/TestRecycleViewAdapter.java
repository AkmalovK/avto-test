package com.example.autotest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class TestRecycleViewAdapter extends RecyclerView.Adapter<TestRecycleViewAdapter.ViewHolder> {

    private int answerIndex;
    Context context;
    List<String> array;
    ArrayList<ViewHolder> views = new ArrayList<>();
    TextView question;
    private SQLiteDatabase db;
    ImageView image;
    Activity activity;
    TestModel model;
    TestRecycleViewAdapter(Context context, List<String> arr, int answerIntex, Activity activity, SQLiteDatabase db, TestModel model) {
        this.answerIndex = answerIntex;
        this.context = context;
        this.array = arr;
        this.activity = activity;
        this.image = activity.findViewById(R.id.test_image);
        this.question = activity.findViewById(R.id.question);
        this.db = db;
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String str = array.get(position);
        holder.textView.setText(str);
        views.add(holder);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.test_item);
            itemView.setOnClickListener(this);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onClick(View view) {
            if (answerIndex == this.getAdapterPosition()) {
                Toast.makeText(context, "To'g'ri javob", Toast.LENGTH_LONG).show();
                db.execSQL(
                        String.format(
                                "INSERT INTO Result(image, question, answer, istrue) VALUES (%d, \"%s\", \"%s\", %d)",
                                model.getImage(), model.getQuestion(), model.getAnswers().get(this.getAdapterPosition()), 1
                                ));
            } else {
                Toast.makeText(context, "Noto'g'ri javob", Toast.LENGTH_LONG).show();
                db.execSQL(
                        String.format(
                                "INSERT INTO Result(image, question, answer, istrue) VALUES (%d, \"%s\", \"%s\", %d)",
                                model.getImage(), model.getQuestion(), model.getAnswers().get(this.getAdapterPosition()), 0
                        ));
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    //remove items
                    for (int i = 0; i < array.size();) {
                        array.remove(i);
                        notifyItemRemoved(i);
                        notifyItemRangeChanged(i, array.size());
                    }
                    model = Tests.getArray();
                    if (model == null) {
                        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                        context.startActivity(intent);
                        return;
                    }
                    question.setText(model.getQuestion());
                    image.setImageResource(model.getImage());
                    answerIndex = model.getAnswerIndex();

                    for (int i = 0; i < model.getAnswers().size(); i++) {
                        array.add(model.getAnswers().get(i));
                        notifyItemInserted(i);
                        notifyItemRangeChanged(i, array.size());
                    }
                }
            }, 1000);
        }
    }
}
