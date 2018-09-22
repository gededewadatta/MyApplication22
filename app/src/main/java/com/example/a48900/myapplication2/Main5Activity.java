package com.example.a48900.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main5Activity extends AppCompatActivity {
    private String url = "http://192.168.137.1:7013/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void onClick(View view) {
        url="http://192.168.137.1:7013/test";
        Intent intent = new Intent(Main5Activity.this, MainActivity.class);
        Main5Activity.this.startActivity(intent);
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
}
