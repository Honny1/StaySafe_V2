package com.example.pc.staysafe.Common;

import android.renderscript.ScriptGroup;
import android.util.Log;

import com.example.pc.staysafe.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by 1AINFG07 on 23/03/2017.
 */

public class Answer {
    public static String GetAnswer(int type, int position, InputStream File) {
        try {
            String language = Locale.getDefault().getLanguage();
            InputStream raw = File;
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
