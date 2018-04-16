package com.example.hemanthkumar.create_event;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    MyDatabase database;
    Context context;
    ArrayList<Data> tabledata;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = findViewById(R.id.recyclerView);
        tabledata=new ArrayList<>();
        context=this;
        database = new MyDatabase(context, "DB", null, 1);
        tabledata = database.getData();
        showDetails(tabledata);
    }

    private void showDetails(ArrayList<Data> tabletwodata) {
        adapter = new MyAdapter(this, tabledata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
