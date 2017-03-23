package com.example.pc.staysafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    //need make login with database
    EditText ed1,ed2,ed3;
    Button b1, b2;
    Intent in, in1;
    String n, ph, x;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button1);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String x="admin";
                if(n.equals(x) && ph.equals(x)){

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Name, n);
                    editor.putString(Phone, ph);
                    editor.commit();
                    in = new Intent(Login.this,HomeActivity.class);
                    startActivity(in);
                }
                else {
                    Toast.makeText(Login.this, "Bad Login or Pass", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in1 = new Intent(Login.this,Register.class);
                startActivity(in1);
            }
        });
    }
}
