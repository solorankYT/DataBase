package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    EditText username, password;
    Button btnlogin;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.login_page);
    username = (EditText) findViewById(R.id.userlogin);
    password = (EditText) findViewById(R.id.passlogin);
    btnlogin = (Button) findViewById(R.id.btnlogin1);

    DB = new DatabaseHelper(this);



    btnlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("LOGIN_TAG", "Button clicked");
            String user = username.getText().toString();
            String pass = password.getText().toString();

        if (user.equals("") || pass.equals("")){
            Toast.makeText(LoginActivity.this, "Can't be blank", Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean check_userpass = DB.check_user(user, pass);
            Log.d("LOGIN_TAG", "Check user password result: " + check_userpass);
            if (check_userpass==true){
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                Log.d("LOGIN_TAG", "Intent started");
            }
            else{
                Toast.makeText(LoginActivity.this, "Invalid!", Toast.LENGTH_SHORT).show();
            }
        }

        }
    });

    }
}
