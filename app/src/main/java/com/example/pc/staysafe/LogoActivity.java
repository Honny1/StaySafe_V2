package com.example.pc.staysafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LogoActivity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        ImageView Imgvw_spsoa= (ImageView)findViewById(R.id.imageView);
        Animation Anim_spsoalogo = AnimationUtils.loadAnimation(this, R.anim.welcome_sr);
        Imgvw_spsoa.startAnimation(Anim_spsoalogo);


        Thread Thrd_wlcmscrndelay = new Thread() {
            public void run() {
                try {
                    sleep(3500);
                    startActivity(new Intent(getBaseContext(),Login.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        Thrd_wlcmscrndelay.start();
    }
}