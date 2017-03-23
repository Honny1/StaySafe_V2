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

import com.example.pc.staysafe.Common.Answer;
import com.example.pc.staysafe.Common.PositionMemory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class DangerActivity extends AppCompatActivity {
    private TextView tips;
    private int Type,Type_Of_Danger;
    private final PositionMemory position = new PositionMemory(5);
    private InputStream file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        Bundle Extra = getIntent().getExtras();
        int Type = Extra.getInt("Type");
        int Type_Of_Danger = Extra.getInt("Type_Of_Danger");
        Log.d("ERRPR","Activitz" +Integer.toString(Type));
        switch(Type_Of_Danger){
            case 0:         file = getBaseContext().getResources().openRawResource(R.raw.danger); break;
            case 1:         file = getBaseContext().getResources().openRawResource(R.raw.avoid_danger); break;
        }
        tips = (TextView) findViewById(R.id.tip);
        //getBaseContext().getResources().openRawResource(R.raw.danger)
        tips.setText(Answer.GetAnswer(Type,position.getPosition(),file));
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
            tips.setText(Answer.GetAnswer(Type,position.getPosition(),file));
        }
    }
    public void question(){
        AlertDialog.Builder myAlert= new AlertDialog.Builder(this);
        myAlert.setMessage(R.string.notify_Title)
                .setPositiveButton(R.string.True, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tips.setText(Answer.GetAnswer(Type,position.getPosition(),file));
                        if (position.incrementPosition()) {
                            tips.setText(Answer.GetAnswer(Type,position.getPosition(),file));
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

}
