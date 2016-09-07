package com.mittudev.boredim;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FindPeople extends AppCompatActivity {
    String eMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_people);


        eMail = GlobalVars.email;

        load();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_find_people_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_print){

            final WebView webView = new WebView(this);
            webView.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    PrintManager printManager= (PrintManager) getSystemService(Context.PRINT_SERVICE);
                    PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

                    String jobName = getString(R.string.app_name) + "Document";
                    PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
                }
            });

            String html = "<html><body><h2 style=\"text-align: center;\">Found 3 People Near You</h2>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<ol>\n" +
                    "<li>Name: NULLInterests: NULL</li>\n" +
                    "<li>Name: NULLInterests: NULL</li>\n" +
                    "<li>Name: NULLInterests: NULL</li>\n" +
                    "</ol>\n" +
                    "<p style=\"text-align: center;\"><a href=\"toboredlygo.com\">toboredlygo.com</a></p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>&nbsp;</p></body></html>";
            webView.loadDataWithBaseURL(null, html, "text/HTML", "UTF-8", null);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void load(){
        final HashMap<String, String>  param = new HashMap<>();
        param.put("email", eMail);

        Tools.addRequestToQueue(this, "http://toboredlygo.com/findUsersNearMe.php", param, new Response.Listener<String>(){
            public void onResponse(String response){
                try {
                    JSONObject json = new JSONObject(response);
                    final JSONArray array = json.getJSONArray("users");

                    Tools.addRequestToQueue(FindPeople.this, "http://toboredlygo.com/getsimilarusers.php", param, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json2 = new JSONObject(response);
                                final JSONArray array2 = json2.getJSONArray("users");

                                ArrayList<String> list = new ArrayList<String>();
                                if (array != null) {
                                    for (int i=0;i<array.length();i++){
                                        list.add(array.get(i).toString());
                                    }
                                }
                                ArrayList<String> list2 = new ArrayList<String>();
                                if (array2 != null) {
                                    for (int i=0;i<array2.length();i++){
                                        list.add(array2.get(i).toString());
                                    }
                                }

                                ArrayList<String> arrayF = new ArrayList<String>();
                                for(int i = 0; i< array.length(); i++){
                                    if(list2.contains(list.get(i))){
                                        if(!arrayF.contains(list.get(i))){
                                            arrayF.add(list.get(i));
                                        }
                                    }
                                }

                                fill(arrayF);



                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void fill(final ArrayList<String> ids) {
        HashMap<String, String> param;
        param = new HashMap<>();
        param.put("user_id", ids.get(0));
        Tools.addRequestToQueue(this, "http://toboredlygo.com/getnames.php", param, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ((TextView)findViewById(R.id.textView1)).setText(new JSONObject(response).getString("fname"));
                    HashMap<String, String> param2 = new HashMap<String, String>();
                    param2.put("user_id", ids.get(0));
                    Tools.addRequestToQueue(FindPeople.this, "http://toboredlygo.com/getuserinterest.php", param2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                ((TextView) findViewById(R.id.textView1)).setText(new JSONObject(response).getString("intrests"));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        param = new HashMap<>();
        param.put("user_id", ids.get(1));
        Tools.addRequestToQueue(this, "http://toboredlygo.com/getnames.php", param, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ((TextView)findViewById(R.id.textView2)).setText(new JSONObject(response).getString("fname"));
                    HashMap<String, String> param2 = new HashMap<String, String>();
                    param2.put("user_id", ids.get(1));
                    Tools.addRequestToQueue(FindPeople.this, "http://toboredlygo.com/getuserinterest.php", param2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                ((TextView) findViewById(R.id.textView1)).setText(new JSONObject(response).getString("intrests"));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        param = new HashMap<>();
        param.put("user_id", ids.get(2));
        Tools.addRequestToQueue(this, "http://toboredlygo.com/getnames.php", param, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ((TextView)findViewById(R.id.textView3)).setText(new JSONObject(response).getString("fname"));
                    HashMap<String, String> param2 = new HashMap<String, String>();
                    param2.put("user_id", ids.get(2));
                    Tools.addRequestToQueue(FindPeople.this, "http://toboredlygo.com/getuserinterest.php", param2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                ((TextView) findViewById(R.id.textView1)).setText(new JSONObject(response).getString("intrests"));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}
