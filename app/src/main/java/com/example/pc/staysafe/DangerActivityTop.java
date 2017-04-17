package com.example.pc.staysafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.staysafe.Common.PositionMemory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class DangerActivityTop extends AppCompatActivity {
    private TextView tips;
    int id;
    private int Type,Type_Of_Danger;
    private final PositionMemory position = new PositionMemory(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger1);
        Bundle Bnd = getIntent().getExtras();
        Type = Bnd.getInt("Type");
        Type_Of_Danger = Bnd.getInt("Type_Of_Danger");
        tips = (TextView) findViewById(R.id.tip);
        switch(Type_Of_Danger){

            case 0:
                id = this.getResources().getIdentifier("danger_internet", "raw", this.getPackageName());
                break;
            case 1:
                id = this.getResources().getIdentifier("avoid_danger_internet", "raw", this.getPackageName());
                break;
            case 2:
                id = this.getResources().getIdentifier("danger_reallife", "raw", this.getPackageName());
                break;
            case 3:
                id = this.getResources().getIdentifier("avoid_danger_reallife", "raw", this.getPackageName());
                break;
            }
            tips.setText(GetAnswer(Type,position.getPosition(),id));
        }

    public void next(View view) {
        question();
    }

    public void back (View view){
        if (position.decrementPosition()) {
            tips.setText(GetAnswer(Type,position.getPosition(),id));
        }
    }
    public void question(){
        AlertDialog.Builder myAlert= new AlertDialog.Builder(this);
        myAlert.setMessage(R.string.notify_Title)
                .setPositiveButton(R.string.True, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tips.setText(GetAnswer(Type,position.getPosition(),id));
                        if (position.incrementPosition()) {
                            tips.setText(GetAnswer(Type,position.getPosition(),id));
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
    public String GetAnswer(int type,int position,int id) {
        try {
            InputStream file = getResources().openRawResource(id);
            String language = Locale.getDefault().getLanguage();
            BufferedReader is = new BufferedReader(new InputStreamReader(file, "UTF8"));
            String Key;
            String a =("msg" + Integer.toString(type) + '.'+ Integer.toString(position)+ '-' +language);
            while((Key = is.readLine())!=null) {
                if (Key.lastIndexOf(a) == 0) {
                    return Key.substring(Key.lastIndexOf('=')+1);
                }
            }return "No string was found";
        } catch (Exception e) {
            return e.toString();
        }
    }
}