package com.mittudev.boredim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Register4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);
    }

    public void goLogin (View view){
        Intent intent = new Intent(Register4.this, Login.class);
        startActivity(intent);
        finish();
    }

}
