package com.mittudev.boredim;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void imBored(View view) {
        Intent intent = new Intent(MainPage.this, FindPeople.class);
        startActivity(intent);
        finish();
    }

    public void goPro(View view) {
        Intent intent = new Intent(MainPage.this, Profile.class);
        startActivity(intent);
        finish();
    }

    public void goSet(View view) {
        Intent intent = new Intent(MainPage.this, Setings.class);
        startActivity(intent);
        finish();
    }
}
