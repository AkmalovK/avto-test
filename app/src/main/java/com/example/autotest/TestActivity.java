package com.example.autotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlinx.coroutines.selects.WhileSelectKt;

public class TestActivity extends AppCompatActivity {

    DbHandler dbHandler;
    SQLiteDatabase db;
    Cursor cursor;
    ArrayList<TestModel> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        dbHandler = new DbHandler(getApplicationContext());
        db = dbHandler.getReadableDatabase();
        db.execSQL("DELETE FROM Result");
        cursor = db.rawQuery("SELECT * FROM Test;", null);
        while (cursor.moveToNext()) {
            String question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            int answerIndex = -1;
            ArrayList<String> answers = new ArrayList<>();
            @SuppressLint("DefaultLocale") String sql = String.format("SELECT * FROM Answers WHERE image = %d", id);
            Cursor c = db.rawQuery(sql, null);
            while (c.moveToNext()) {
                int isTrue = c.getInt(c.getColumnIndexOrThrow("IsTrue"));
                String answer = c.getString(c.getColumnIndexOrThrow("Name"));
                if (isTrue == 1) {
                    answerIndex = answers.size();
                }
                answers.add(answer);
            }
            array.add(new TestModel(
                question,
                answerIndex,
                answers,
                image
            ));
        }

        Tests.SetTestModel(array);

        TestModel model = Tests.getArray();

        RecyclerView rView = findViewById(R.id.recycle_view_test);

        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(
                this,
                model.getAnswers(),
                model.getAnswerIndex(),
                this,
                db,
                model
        );
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setAdapter(adapter);

        TextView title = findViewById(R.id.question);
        title.setText(model.getQuestion());

        ImageView image = findViewById(R.id.test_image);
        image.setImageResource(model.getImage());
        TextView timer = findViewById(R.id.timer_text);

        new CountDownTimer(10*60*1000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            @SuppressLint("SetTextI18n")
            public void onFinish() {

                timer.setText("00:00:00");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }.start();
    }
}