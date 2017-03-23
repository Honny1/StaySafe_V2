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

public class AvoidReallifeActivity extends AppCompatActivity {
    private TextView tips;
    private final PositionMemory position = new PositionMemory(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avoid_reallife);

        tips = (TextView) findViewById(R.id.tip);
        tips.setText("Avoid Danger " + Integer.toString(position.getPosition()));
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
            tips.setText("Avoid Danger " + Integer.toString(position.getPosition()));
        }
    }
    public void question(){
        AlertDialog.Builder myAlert= new AlertDialog.Builder(this);
        myAlert.setMessage("Question??")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tips.setText("Avoid Danger" + Integer.toString(position.getPosition()));
                        if (position.incrementPosition()) {
                            tips.setText("Avoid Danger" + Integer.toString(position.getPosition()));
                        } else {
                            startActivity(new Intent(getApplicationContext(), TestActivity.class));
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),"Bad Answer!", Toast.LENGTH_SHORT).show();

                    }
                });
        myAlert.show();
    }
}