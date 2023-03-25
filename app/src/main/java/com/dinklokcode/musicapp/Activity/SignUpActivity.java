package com.dinklokcode.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dinklokcode.musicapp.Database.DBHelper;
import com.dinklokcode.musicapp.Fragment.LibraryFragment;
import com.dinklokcode.musicapp.R;

public class SignUpActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.username);
        password =  (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                Bundle b = new Bundle();
                b.putInt("kt",1);
                i.putExtra("kt",b);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert ==true) {
                                Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                                Bundle b = new Bundle();
                                b.putInt("kt",1);
                                i.putExtra("kt",b);
                                Log.d("KT","__1");
                                startActivity(i);
                            }else{
                                Toast.makeText(SignUpActivity.this, "Registered failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}