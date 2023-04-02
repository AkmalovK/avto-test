package com.example.autotest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {
    DbHandler dbHandler;
    SQLiteDatabase db;
    ArrayList<ResultModel> array = new ArrayList<>();

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        dbHandler = new DbHandler(getContext());
        db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Result", null);

        while (cursor.moveToNext()) {
            array.add(
                    new ResultModel(
                            cursor.getInt(cursor.getColumnIndexOrThrow("image")),
                            cursor.getString(cursor.getColumnIndexOrThrow("question")),
                            cursor.getString(cursor.getColumnIndexOrThrow("answer")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("istrue"))
                    )
            );
        }

        RecyclerView rView = view.findViewById(R.id.result_recycle_view);
        ResultRecycleViewAdapter adapter = new ResultRecycleViewAdapter(
                array,
                view.getContext()
        );

        rView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rView.setAdapter(adapter);
        return view;
    }
}