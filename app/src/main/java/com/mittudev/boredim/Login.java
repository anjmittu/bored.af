package com.mittudev.boredim;

import android.content.Intent;
import android.graphics.Color;
import android.location.GpsStatus;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;


public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File f = new File("./login.txt");
        if (f.exists() && !f.isDirectory()) {
            goMain();
        } else {
            setContentView(R.layout.activity_login);
        }

    }

    public void lgin (View view) {
        EditText usrName = (EditText) findViewById(R.id.usrNm);
        EditText pass = (EditText) findViewById(R.id.liPass);

        chkLgin(usrName.getText().toString(), pass.getText().toString());

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public void isCor () {
        EditText usrName = (EditText) findViewById(R.id.usrNm);
        EditText pass = (EditText) findViewById(R.id.liPass);

        if (!isEmpty(usrName) && !isEmpty(pass)) {
            try {
                FileWriter out = new FileWriter("./login.txt");
                out.append(usrName.getText().toString());
                out.append("\n");
                out.append(pass.getText().toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            goMain();
        } else {
            if (isEmpty(usrName)) {
                usrName.setHintTextColor(Color.parseColor("#ff0000"));
            }
            if (isEmpty(pass)) {
                pass.setHintTextColor(Color.parseColor("#ff0000"));

            }

        }
    }

    public void isWon () {
        TextView wrong = (TextView) findViewById(R.id.wrong);
        wrong.setText("Wrong Password and/or username.");
    }

    public void chkLgin (String usr, String pass) {

        HashMap<String, String> param = new HashMap<>();
        param.put("email", usr);
        param.put("password", pass);

        Tools.addRequestToQueue(this, "http://toboredlygo.com/login.php", param, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jResponse = new JSONObject(response);
                    if (jResponse.getBoolean("verified")) {
                        EditText usrName = (EditText) findViewById(R.id.usrNm);
                        Intent intent = new Intent(Login.this, MainPage.class);

                        Bundle b = new Bundle();
                        b.putString("email", usrName.getText().toString());
                        intent.putExtras(b);

                        startActivity(intent);
                        finish();
                    } else {
                        isWon();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goMain () {
        EditText usrName = (EditText) findViewById(R.id.usrNm);
        EditText pword = (EditText) findViewById(R.id.liPass);
        chkLgin(usrName.getText().toString(), pword.getText().toString());
    }

    public void goRgstr (View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();

    }

}
