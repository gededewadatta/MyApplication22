package com.example.a48900.myapplication2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.a48900.myapplication2.response.PendingQuestionResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.AbstractCollection;

import javax.xml.transform.stream.StreamResult;
//import com.example.a48900.ledsistem.response;

public class Main3Activity extends AppCompatActivity {
    Button button_login;
    EditText username;
    EditText password;
    String answerType;



    EditText txt_username, txt_password;
    private String url = "http://192.168.137.1:7013/led/api/automation/search/employee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
       // answerType = getQuestion(username.getText().toString());
        //method pindah layout
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {


            public String Content;
            public static final String TAG = "test" ;

            @Override
            public void onClick(View view) {
                String question = getQuestion(username.getText().toString());
               if(!question.equals("")){
                Intent intent = new Intent(Main3Activity.this, Main5Activity.class);
                Main3Activity.this.startActivity(intent);}
//                if (answerType.equals("freetext")) {
//                    Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
//                    intent.putExtra("username", username.getText().toString());
//                    startActivity(intent);
//                } else {
//                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                    i.putExtra("username", username.getText().toString());
//                    startActivity(i);
//                }
            }

            private String getQuestion(String username) {
                String serviceUrl = "http://192.168.137.1:7013/led/api/automation/search/employee";
                URL url = null;
                String input = "username=" + username;
                BufferedReader reader=null;
                try {
                    Log.d(TAG, "call rest service to get json response");
                    url = new URL(serviceUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(4000);
                    connection.setReadTimeout(4000);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("charset", "utf-8");
                    connection.setRequestProperty("Content-Length", "" + Integer.toString(input.getBytes().length));
                    connection.connect();
                    StreamResult conn = null;
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(input);
                    os.flush();
                    //os.close();
                    if (connection.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null)
                    {
                        // Append server response in string
                        sb.append(line + " ");
                    }

                    // Append Server Response To Content String
                    Content = sb.toString();

                    //pass buffered reader to convert json to java object using gson
                    return convertJsonToObject(bufferedReader);

                } catch (Exception e) {
                    Log.e(TAG, "error in getting and parsing response");
                }
                return null;

            }

            public String convertJsonToObject(BufferedReader bufferedReader) {
                //instantiate Gson
                final Gson gson = new Gson();

                //pass root element type to fromJson method along with input stream
                PendingQuestionResponse pendingQuestionResponse = gson.fromGson(bufferedReader, PendingQuestionResponse.class);

                String answerType = pendingQuestionResponse.answerType();
                AbstractCollection cpnlst;
                Log.e(TAG, "number of coupons from json response after gson parsing" );

                return answerType;
            }

        });
    }
}