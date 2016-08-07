package com.mittudev.boredim;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public void goRgstr2 (View view) {
        EditText fName = (EditText) findViewById(R.id.fName);
        EditText lName = (EditText) findViewById(R.id.lName);
        EditText email = (EditText) findViewById(R.id.email);

        if (!isEmpty(fName) && !isEmpty(lName) && !isEmpty(email)) {
            Bundle info = new Bundle();
            info.putStringArray("info", new String[]{fName.getText().toString(), lName.getText().toString(), email.getText().toString()});
            Intent intent = new Intent(Register.this, Register2.class);
            intent.putExtras(info);
            startActivity(intent);

        } else {
            if (isEmpty(fName)) {
                fName.setHintTextColor(Color.parseColor("#ff0000"));
            }
            if (isEmpty(lName)) {
                lName.setHintTextColor(Color.parseColor("#ff0000"));
            }
            if (isEmpty(email)) {
                email.setHintTextColor(Color.parseColor("#ff0000"));
            }

        }




    }


}
