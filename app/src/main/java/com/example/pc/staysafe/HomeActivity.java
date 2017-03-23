package com.example.pc.staysafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * The method will take care of all button belonging to HomeActivity activity
     *
     * Specifying this method in android:onclick in .xml file is needed, id of the buttons required
     * <b>WARNING</b>: if button that use this method doesn`t have an id, app will crash clicked
     * Recommendation: use this method only to start activities & very simple stuff; max 10 lines
     * each case statement maybe?
     *
     * @param view the button which id used
     */
    public void onHomeButtonClick(View view) {
        Bundle extras = new Bundle();
        extras.putInt("Type",0);
        extras.putInt("Type_Of_Danger",0);

        switch (view.getId()) {
            case R.id.Home_btn_dangers:
                Log.w(getLocalClassName(), "Home_btn_dangers");
                chooseTopicDialog();
                break;

            case R.id.Home_btn_avoid:
                Log.w(getLocalClassName(), "Home_btn_avoid");
                chooseTopicDialog2();
                break;

            case R.id.Home_btn_tips:
                Log.w(getLocalClassName(), "Home_btn_tips");

                // No activity yet
                Toast.makeText(this, "No activity yet", Toast.LENGTH_SHORT).show();
                break;


            case R.id.dialog_home_btn_internet:
                Log.w(getLocalClassName(), "danger_btn_internet");
                extras.putInt("Type",0);
                extras.putInt("Type_Of_Danger",0);
                startActivity(new Intent(getBaseContext(), DangerActivity.class).putExtras(extras));
                break;

            case R.id.dialog_home_btn_realLife:
                Log.w(getLocalClassName(), "danger_btn_realLife");
                extras.putInt("Type",0);
                extras.putInt("Type_Of_Danger",1);
                startActivity(new Intent(getBaseContext(), DangerActivity.class).putExtras(extras));
                break;

            case R.id.avoid_dialog_home_btn_internet:
                Log.w(getLocalClassName(), "Avoid_btn_Intenet");
                extras.putInt("Type",1);
                extras.putInt("Type_Of_Danger",0);
                startActivity(new Intent(getBaseContext(), DangerActivity.class).putExtras(extras));
                break;


            case R.id.avoid_dialog_home_btn_realLife:
                extras.putInt("Type",1);
                extras.putInt("Type_Of_Danger",1);
                Log.w(getLocalClassName(), "Avoid_btn_RealLife");
                startActivity(new Intent(getBaseContext(), DangerActivity.class).putExtras(extras));
                break;

            case R.id.logout:
                Log.w(getLocalClassName(), "logout");
                SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getBaseContext(), Login.class));
                break;
        }
    }

    /**
     * Show the DialogAlert
     */
    private void chooseTopicDialog() {
        View view = View.inflate(this, R.layout.dialog_home_choose, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        (dialog = dialogBuilder.setView(view).create()).show();
        ((Button) view.findViewById(R.id.dialog_home_btn_internet)).setOnClickListener(new CustomOnClickListener());
        ((Button) view.findViewById(R.id.dialog_home_btn_realLife)).setOnClickListener(new CustomOnClickListener());
    }

    private void chooseTopicDialog2() {
        View view = View.inflate(this, R.layout.dialog_home_choose2, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        (dialog = dialogBuilder.setView(view).create()).show();
        ((Button) view.findViewById(R.id.avoid_dialog_home_btn_internet)).setOnClickListener(new CustomOnClickListener());
        ((Button) view.findViewById(R.id.avoid_dialog_home_btn_realLife)).setOnClickListener(new CustomOnClickListener());
    }

    /**
     * Custom class
     *
     * Buttons in view need OnClickListener to act, this class act like onClickListener, but
     * onClick method start the {@link #onHomeButtonClick(View)}, which take care of the buttons
     */
    private class CustomOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            dialog.cancel();
            onHomeButtonClick(view);
        }
    }
}
