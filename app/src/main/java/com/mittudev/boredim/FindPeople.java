package com.mittudev.boredim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class FindPeople extends AppCompatActivity {
    String eMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_people);

        Bundle b = this.getIntent().getBundleExtra("email");
        String[] email = b.getStringArray("info");
        eMail = email[0];

    }

    private void load(){
        HashMap<String, String>  param = new HashMap<>();
        param.put("email", eMail);

        Tools.addRequestToQueue(this, "http://toboredlygo.com/findUsersNearMe.php", param, new Response.Listener<String>(){
            public void onResponse(String response){
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray array = json.getJSONArray("users");
                    ((TextView) findViewById(R.id.textView1)).setText(array.getString(0));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


}
