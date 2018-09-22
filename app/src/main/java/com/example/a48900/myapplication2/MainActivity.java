package com.example.a48900.myapplication2;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private Button button_login;

    private RequestQueue requestQueue;
    private String register="";
    private EditText edittextJSON;
    private EditText txt_username;
    private EditText txt_password;
    private static final String JSON_URL = "http://10.17.93.171/automation/api/search/pendingquestion/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        button_login = (Button) findViewById(R.id.button_login);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        register = txt_username.getText().toString().trim();
        register = txt_password.getText().toString().trim();
        button_login.setOnClickListener((View.OnClickListener) this);
    }

    public void checkRegister() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if(jsonResponse.getString("msg").equalsIgnoreCase("Success")){
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                MainActivity.this.startActivity(intent);
                                Toast.makeText(MainActivity.this, jsonResponse.getString("msg") , Toast.LENGTH_LONG).show();
                            }else{
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                MainActivity.this.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Invalid Reg Number!", Toast.LENGTH_LONG).show();
                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                register = edittextJSON.getText().toString();
                params.put("username", register);
                params.put("password", register);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void onClick(View v) {
        checkRegister();
    }
}
