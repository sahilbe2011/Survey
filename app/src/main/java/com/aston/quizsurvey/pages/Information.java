package com.aston.quizsurvey.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aston.quizsurvey.R;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;

public class Information extends AppCompatActivity {
     private Button next;
    TextInputEditText name,number,village;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        name=findViewById(R.id.infoname);
        number=findViewById(R.id.infronumber);
        village=findViewById(R.id.infrovillage);
        next=findViewById(R.id.infrostart);
        String id=getIntent().getStringExtra("agent_id");
        String ssurveynumber=getIntent().getStringExtra("survey");
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.infoname, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.infronumber, RegexTemplate.NOT_EMPTY, R.string.invalid_number);
        awesomeValidation.addValidation(this, R.id.infrovillage, RegexTemplate.NOT_EMPTY, R.string.invalid_data);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Intent su = new Intent(Information.this, Questions.class);
                    su.putExtra("name", name.getText().toString());
                    su.putExtra("number", number.getText().toString());
                    su.putExtra("village", village.getText().toString());
                    su.putExtra("survey", ssurveynumber);
                    su.putExtra("agent_id", id);
                    startActivity(su);

                }
            }
        });
    }
}