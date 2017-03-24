package com.example.pc.staysafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.staysafe.Common.PositionMemory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class DangerActivity2 extends AppCompatActivity {

    private TextView tips;
    private int Type;
    private final PositionMemory position = new PositionMemory(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        Type = getIntent().getIntExtra("Type", 0);
        tips = (TextView) findViewById(R.id.tip);
        tips.setText(GetAnswer(Type,position.getPosition()));
    }

    public void next(View view) {
        question();
       /* if (position.incrementPosition()) {
            tips.setText("Danger" + Integer.toString(position.getPosition()));
        } else {
            startActivity(new Intent(this, TestActivity.class));
        }*/
    }

    public void back (View view){
        if (position.decrementPosition()) {
            tips.setText(GetAnswer(Type,position.getPosition()));
        }
    }
    public void question(){
        AlertDialog.Builder myAlert= new AlertDialog.Builder(this);
        myAlert.setMessage(R.string.notify_Title)
                .setPositiveButton(R.string.True, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tips.setText(GetAnswer(Type,position.getPosition()));
                        if (position.incrementPosition()) {
                            tips.setText(GetAnswer(Type,position.getPosition()));
                        } else {
                            startActivity(new Intent(getApplicationContext(), TestActivity.class));
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.False, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),R.string.Notify_BadAn, Toast.LENGTH_SHORT).show();

                    }
                });
        myAlert.show();
    }
    public String GetAnswer(int type,int position) {
        try {
            String language = Locale.getDefault().getLanguage();
            InputStream raw = getBaseContext().getResources().openRawResource(R.raw.danger_reallife);
            BufferedReader is = new BufferedReader(new InputStreamReader(raw, "UTF8"));
            String Key;
            String a =("msg" + Integer.toString(type) + '.'+ Integer.toString(position)+ '-' +language);
            while((Key = is.readLine())!=null) {
                Log.d("ERROR", a);
                Log.d("ERROR", Integer.toString(Key.lastIndexOf(a)));
                if (Key.lastIndexOf(a) == 0) {
                    return Key.substring(Key.lastIndexOf('=')+1);

                }
            }return "No string was found";
        } catch (Exception e) {
            return e.toString();
        }
    }
}

