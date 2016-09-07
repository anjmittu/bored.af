package com.mittudev.boredim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Groups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        int nGrp = 10;

        for (int i = 0; i <= nGrp; i++) {
        }
    }


    public void mkGroup (View view) {
        Bundle b = this.getIntent().getBundleExtra("email");

        Intent intent = new Intent(Groups.this, FindPeople.class);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

}
