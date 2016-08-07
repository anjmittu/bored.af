package com.mittudev.boredim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Register3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
    }

    public void goRgstr4 (View view) {

        EditText pass = (EditText) findViewById(R.id.pass);
        EditText passR = (EditText) findViewById(R.id.passR);
        TextView nPass = (TextView) findViewById(R.id.passNoM);
        TextView test = (TextView) findViewById(R.id.test);

        if (pass.getText().toString().matches(passR.getText().toString())){

            Bundle info = this.getIntent().getExtras();
            String[] pInfo = info.getStringArray("info");

            HashMap<String, String> param = new HashMap<>();
            param.put("fname", pInfo[0]);
            param.put("lname", pInfo[1]);
            param.put("email", pInfo[2]);
            param.put("bday", pInfo[3]);
            param.put("gender", pInfo[4]);
            param.put("pass", pass.getText().toString());

           Tools.addRequestToQueue(this, "http://toboredlygo.com/newuser.php", param, new Response.Listener<String>() {public void onResponse(String response) {}});

            Intent intent = new Intent(Register3.this, Register4.class);
            startActivity(intent);
            finish();

        } else {
            nPass.setText("Your passwords don't match.");
        }


    }
}
