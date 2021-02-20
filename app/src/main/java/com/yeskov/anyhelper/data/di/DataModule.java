package com.yeskov.anyhelper.data.di;

import android.content.Context;

import com.yeskov.anyhelper.data.DataRepository;
import com.yeskov.anyhelper.data.Prefs;

import toothpick.config.Module;

public class DataModule extends Module {

    public DataModule(Context context) {
        // SharedPreferences
        Prefs prefs = new Prefs(context);
        // Repository
        bind(DataRepository.class).toInstance(new DataRepository(prefs));
    }
}
