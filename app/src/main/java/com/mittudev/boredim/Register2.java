package com.mittudev.boredim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    public void goRgstr3 (View view) {

       CalendarView bDate = (CalendarView) findViewById(R.id.bDate);
        RadioGroup gender = (RadioGroup) findViewById(R.id.gender);

        int gender_ =  gender.getCheckedRadioButtonId();
        String gender__;
        if (gender_ == R.id.male){
            gender__ = "M";
        } else if (gender_ == R.id.female){
            gender__ = "F";
        } else if (gender_ == R.id.other){
            gender__ = "O";
        } else {
            gender__ = "";
        }


        Bundle info = this.getIntent().getExtras();
        String[] pInfo = info.getStringArray("info");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String bDate_ = sdf.format(new Date(bDate.getDate()));

        String[] nInfo_ = {pInfo[0], pInfo[1], pInfo[2], bDate_, gender__};

        Bundle nInfo = new Bundle();
        nInfo.putStringArray("info", nInfo_);

        Intent intent = new Intent(Register2.this, Register3.class);
        intent.putExtras(nInfo);
        startActivity(intent);
        finish();
    }
}
