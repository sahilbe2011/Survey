package com.aston.quizsurvey.pages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.aston.quizsurvey.R;

public class StartSurvey extends AppCompatActivity {
    Button button;
    TextView textView;
    int p=0;
    int d;
    String id1;
    androidx.appcompat.widget.Toolbar toolbar;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Number = "nameKey";
    private final static int MY_REQUEST_CODE = 1;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_survey);
//        int p=0;
        toolbar = findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        button=findViewById(R.id.startsuryvey);
        textView=findViewById(R.id.secondpagecontent);
//        String dm=getIntent().getStringExtra("number");
        String id=getIntent().getStringExtra("agent_id");
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString(Number,String.valueOf(p));
//        editor.commit();
//        if(id.equalsIgnoreCase(null)) {
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(StartSurvey.this);
//            id1=preferences.getString("id", "0");
//
//        }else if(!id.equalsIgnoreCase("0"))
//        {
//
//
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(StartSurvey.this);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("id", id);
//            editor.apply();
//
//            editor.commit();
//        }

        shownumber();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(StartSurvey.this,Information.class);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(StartSurvey.this);
                String name = preferences.getString("number", "0");
                String id1=preferences.getString("id", "0");
                int d=Integer.valueOf(name);
                if (d>0) {
                    start.putExtra("survey", name);
                    start.putExtra("agent_id", id1);
                    startActivity(start);

                }
                else
                {
                    start.putExtra("survey", String.valueOf(p));
                    start.putExtra("agent_id", id);
                    startActivity(start);

                }

//                startActivityForResult(start, 1);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                if (d>1){
//                    String number=sharedpreferences.getString(Number,"0");
//
//                    d=Integer. valueOf(number);
//                    d++;
//                    editor.putString(Number,String.valueOf(d));
//                    editor.commit();
//                }else {
//                    editor.putString(Number, String.valueOf(p));
//                    editor.commit();
//                }
                startActivity(start);
            }
        });
//        button.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP) {
//                    button.setBackgroundColor(Color.RED);
//                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
//                    button.setBackgroundColor(Color.BLUE);
//                }
//                return false;
//            }
//        });
    }

    private void shownumber() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("number", "0");

//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        String number=sharedpreferences.getString(Number,"0");

//         d=Integer.valueOf(number);
        if (name==null){
            textView.setText("You Have taken Total "+p+" Survey");
        }
        else

        textView.setText("You Have taken Total "+name+" Survey");
//        textView.setText("You Have taken Total "+p+" Survey");
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if(resultCode == RESULT_OK) {
//                String strEditText = data.getStringExtra("editTextValue");
//                int d= Integer.valueOf(strEditText);
//                d++;
//                textView.setText("You Have taken Total "+d+" Survey");
//
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolmenu, menu);

        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolexit:
                display("Return to Main Menu");
                Intent returnBtn = new Intent(StartSurvey.this,MainActivity.class);
                startActivity(returnBtn);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void display(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    }