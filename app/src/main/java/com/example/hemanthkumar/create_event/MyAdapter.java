package com.example.hemanthkumar.create_event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hemanthkumar on 23/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    ArrayList<Data> tabledata;
    Context context;
    MyDatabase database;
    String title, details, date, time;


    public MyAdapter(Context context, ArrayList<Data> tabledata) {
        database = new MyDatabase(context, "DB", null, 1);
        this.tabledata = tabledata;
        this.context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        MyAdapter.MyViewHolder viewholder = new MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(tabledata.get(position).getTitle());
        holder.details.setText(tabledata.get(position).getDetails());
        holder.date.setText(tabledata.get(position).getDate());
        holder.time.setText(tabledata.get(position).getTime());
        final int pos = position;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = tabledata.get(position).getTitle();
                details = tabledata.get(position).getDetails();
                date = tabledata.get(position).getDate();
                time = tabledata.get(position).getTime();
                delete(title,details,date,time);
                tabledata.remove(tabledata.get(pos));
                notifyDataSetChanged();
            }
        });


    }

    private void delete(String title, String details, String date, String time) {
        database.delete(title,details,date,time);
    }


    @Override
    public int getItemCount() {
        return tabledata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, details, date, time;

        ImageButton delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            details = itemView.findViewById(R.id.tv_details);
            date = itemView.findViewById(R.id.tv_date);
            time = itemView.findViewById(R.id.tv_time);
            delete = itemView.findViewById(R.id.ib_delete);



        }
    }
}
