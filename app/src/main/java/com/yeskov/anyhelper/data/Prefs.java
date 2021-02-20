package com.yeskov.anyhelper.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {

    private final static String SHARED_TEST = "TEST";
    private final SharedPreferences sp;

    public Prefs(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreferences getSp() {
        return sp;
    }

    public void setTest(String phone) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SHARED_TEST, phone);
        editor.apply();
    }

    public String getTest() {
        return sp.getString(SHARED_TEST, "");
    }
}
