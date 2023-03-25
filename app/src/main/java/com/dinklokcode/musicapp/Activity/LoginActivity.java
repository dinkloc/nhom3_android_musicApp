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

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin,btnsignin;
    DBHelper DB;
    int kt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        Intent t = getIntent();
        if(t!=null && t.hasExtra("kt")){
            Bundle d = t.getBundleExtra("kt");
            kt = d.getInt("kt");
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText() .toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        if(kt==0){
                            Intent intent = new Intent(LoginActivity.this,LibraryFragment.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username", user);
                            intent.putExtra("acc",bundle);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                        else{
                            Log.d("CCC","cos");
                            Intent t = new Intent(LoginActivity.this,MainActivity.class);
                            Bundle d = new Bundle();
                            d.putString("username", user);
                            t.putExtra("acc",d);
                            startActivity(t);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnsignin = (Button) findViewById(R.id.btndangki);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(t);
            }
        });
    }
}