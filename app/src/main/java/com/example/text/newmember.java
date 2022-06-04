package com.example.text;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class newmember extends AppCompatActivity {
    private Button button1;
    EditText  editCusid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmember);
        button1 = findViewById(R.id.user);
      //  final EditText edt=(EditText)findViewById(R.id.et_1);
       // String cus_id =edt.getText().toString();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                System.out.println("7788887ss");
               // System.out.println(cus_id);
                new thdConnectDB().start();
            }
        });


    }



    public class thdConnectDB extends Thread{


        @Override
        public void run() {
            super.run();

            try {
                String strSql = "select * from member";
               // URL url = new URL(" http://10.0.2.2:8080/select.jsp");
               // final EditText id=(EditText)findViewById(R.id.et_1);
                //final EditText pw=(EditText)findViewById(R.id.et_2);
               // String cus_id = id.getText().toString();
               // String cus_pw = pw.getText().toString();
                String strURL = "http://10.0.2.2:8080/insert.jsp?cus_id=test3&cus_pw=qweqwe";

                URL url = new URL(strURL);
                //insert into(member_id,member_name) values(b,234);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.connect();

                int responseCode = connection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    String line = null;
                    boolean isWork = false;
                    while ((line = bufferedReader.readLine()) != null){
                        if(line.equals("<body>")) {isWork = true;}
                        if (isWork){
                            String[] splitted = line.split(",");
                            for (int i=0; i<splitted.length; i++){
                                System.out.println(splitted[i]);
                            }
                        }

                    }
                    inputStream.close();
                }
            } catch (Exception e){
                System.out.println(e.toString());
                System.out.println(e.toString());
            }




        }
    }
}

