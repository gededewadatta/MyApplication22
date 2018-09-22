package com.example.a48900.myapplication2;

import android.content.Intent;
import android.icu.text.StringSearch;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.stream.StreamResult;

public class Main2Activity extends Activity {

    private EditText txtUser;

    private EditText txtPassword;

    private EditText txtStatus;

    private Button btnLogin;

    String username;

    String password;


    private String url = "http://192.168.137.1:7013/led/api/automation/search/employee";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtStatus = (EditText) findViewById(R.id.txtStatus);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        //daftarkan even onClick pada btnLogin

        btnLogin.setOnClickListener(new Button.OnClickListener(){


            public void onClick(View v){

                url="http://192.168.137.1:7013/led/automation/api/search/employee?";
                url+="username="+txtUser.getText().toString()+"&password="+txtPassword.getText().toString();
                if(!getRequest(txtStatus,url,txtUser.getText().toString(),txtPassword.getText().toString()).equals("unconnect")){
//                 if(!getRequest(txtStatus,url).equals("unconnect")){
                    Intent intent = new Intent(Main2Activity.this, Main5Activity.class);
                Main2Activity.this.startActivity(intent);}
                else{

                    txtUser.setText(null);
                    txtPassword.setText(null);
                }
            }

        });
    }

    public String getRequest(EditText txtResult, String SUrl,String username,String password){
//        public String getRequest(EditText txtResult, String SUrl){


        Log.d("getRequest",url);

        HttpClient client = new DefaultHttpClient();
         HttpPost request = new HttpPost(SUrl);



        try{

            HttpResponse response = client.execute(request);

            txtResult.setText(request(response));

        }catch(Exception ex){

            txtResult.setText("unconnect");

        }
        String url2 = SUrl+"?username="+username+"&password="+password;
        URL url = null;

        BufferedReader reader=null;

        try {
            String input = URLEncoder.encode("username","UTF-8")+"=" + URLEncoder.encode(username,"UTF-8");
            input+= URLEncoder.encode("password","UTF-8")+"=" + URLEncoder.encode(password,"UTF-8");
            Log.d("log",input);

            url = new URL(SUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("charset", "utf-8");
//             connection.setRequestProperty("Content-Length", "" + Integer.toString(input.getBytes().length));
        //    connection.connect();
//            OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
//            os.write(input);
//            os.flush();
            //os.close();
          //  Uri.Builder builder = new Uri.Builder().appendQueryParameter("username",username).appendQueryParameter("password",password);
          //  String query = builder.build().getEncodedQuery();

            connection.connect();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            //writer.write(query);
            //writer.flush();
            //writer.close();
            os.close();
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }
            String respCode = String.valueOf(connection.getResponseCode());
            Log.d("response code", respCode);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while((line = bufferedReader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + " ");
            }

            // Append Server Response To Content String
       //     Content = sb.toString();
                txtResult.setText(sb.toString());
            //pass buffered reader to convert json to java object using gson
            //return convertJsonToObject(bufferedReader);
             return  "ok";

        } catch (Exception e) {
            txtResult.setText("");
        }
    return txtResult.getText().toString();


    }

//    @Override
//    public String toString() {
//        return "Employee [username=" + username + ", password=" + password  + "]";
//    }

    public  String request(HttpResponse response){

        String result = "";

        try{

            InputStream in = response.getEntity().getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder str = new StringBuilder();

            String line = null;

            while((line = reader.readLine()) != null){

                str.append(line + "\n");

            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";

        }

        return result;
    }

}

