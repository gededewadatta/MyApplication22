package com.example.a48900.myapplication2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Main6Activity extends AppCompatActivity implements View.OnClickListener{

    String url = "http://192.168.137.1:7013/led/automation/api/search/employee";
    RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
    Button login;
    EditText username,password;
    String login1;
    String login2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        login = (Button) findViewById(R.id.button_login);
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        login1 = username.getText().toString().trim();
        login2 = username.getText().toString().trim();
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if(jsonResponse.getString("msg").equalsIgnoreCase("Success")){
                        Intent intent = new Intent(Main6Activity.this, MainActivity.class);
                        Main6Activity.this.startActivity(intent);
                        Toast.makeText(Main6Activity.this, jsonResponse.getString("msg") , Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent = new Intent(Main6Activity.this, Main6Activity.class);
                        Main6Activity.this.startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "gagal login!", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("username", "username");
                MyData.put("password", "password");//Add the data you'd like to send to the server.
                return MyData;
            }
        };
    }

   // MyRequestQueue.add(MyStringRequest);
}

