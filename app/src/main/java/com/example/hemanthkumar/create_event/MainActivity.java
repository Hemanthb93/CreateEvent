package com.example.hemanthkumar.create_event;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView titleone, titletwo, selectDate, selecttime;
    EditText EventName, EventDetails;
    Switch setalam, setremaindering;
    ImageButton list, select;
    private int year, month, day;
    Context context;
    TextView datedisplay, timedisplay;

    MyDatabase database;
    String e_name, e_details;
    String e_date, e_time;
    int hour, minute;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();
        titleone = findViewById(R.id.tv_title);
        titletwo = findViewById(R.id.tv_titletwo);
        datedisplay = findViewById(R.id.tv_datedisplay);
        timedisplay = findViewById(R.id.tv_displaytime);

        EventName = findViewById(R.id.et_Eventname);
        EventDetails = findViewById(R.id.et_Eventdetails);
        selectDate = findViewById(R.id.et_Selectdate);
        selecttime = findViewById(R.id.et_selecttime);
        setalam = findViewById(R.id.alarm);
        setremaindering = findViewById(R.id.setremainder);
        list = findViewById(R.id.im_eventList);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });
        select = findViewById(R.id.im_select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_name = EventName.getText().toString();
                e_details = EventDetails.getText().toString();
                e_date = datedisplay.getText().toString();
                e_time = timedisplay.getText().toString();
                insert(e_name, e_details, e_date, e_time);
            }
        });
        context = this;


        setalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();


            }
        });


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        populateSetDate(selectedyear, selectedmonth + 1, selectedday);
                    }
                }, year, month, day);
                mDatePicker.show();
            }

        });

        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timedisplay.setText(selectedHour + ":" + selectedMinute);
                        minute = selectedMinute;
                    }
                }, hour, minute, true);
                mTimePicker.show();

            }
        });


    }

    private void populateSetDate(int selectedyear, int i, int selectedday) {
        datedisplay.setText(selectedyear + "-" + i + "-" + selectedday);

    }

    private void setAlarm() {
        Toast.makeText(getApplicationContext(), "Alarm On", Toast.LENGTH_SHORT).show();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day,hour,minute);

        Toast.makeText(getApplicationContext(), "Hour Minute"+hour+" "+minute, Toast.LENGTH_SHORT).show();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, AlamReceiver.class);
        intent.setAction("com.example.helloandroid.alarms");

        //....
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

    }


    private void insert(String e_name, String e_details, String e_date, String e_time) {
        database = new MyDatabase(context, "DB", null, 1);
        database.insert(e_name, e_details, e_date, e_time);
    }
}
