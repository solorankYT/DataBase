package com.example.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignupPage extends AppCompatActivity {
    EditText editUserID;
    EditText editUsername;
    EditText editUserPassword;

    Button login;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);



    editUsername = (EditText) findViewById(R.id.editUsername);
    editUserPassword = (EditText) findViewById(R.id.editUserPassword);
    login = findViewById(R.id.loginbtn1);

    dbManager = new DatabaseManager(this);
        try {
        dbManager.open();
    } catch (Exception e) {
        e.printStackTrace();
    }

        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }
    });

}
    public void btnInsert (View v){
        dbManager.insert(editUsername.getText().toString(), editUserPassword.getText().toString());
    }









    public void btnFetched(View v){
        Cursor cursor = dbManager.fetch();

        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_ID));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD));
                Log.i("DATABASE_TAG", "ID: " + ID + " USERNAME: " + username + " Password: " + password);
            } while (cursor.moveToNext());
        }
    }


    public void btnUpdate (View v){
        dbManager.update(Long.parseLong(editUserID.getText().toString()), editUsername.getText().toString(), editUserPassword.getText().toString());
    }

    public void btnDelete (View v){
        dbManager.delete(Long.parseLong(editUserID.getText().toString()));
    }



}

