package com.example.pc.staysafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {
    Button b3;
    Intent in1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        b3=(Button)findViewById(R.id.button2);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in1 = new Intent(TestActivity.this,HomeActivity.class);
                startActivity(in1);
                finish();
            }
        });
    }
}
