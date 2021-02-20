package com.yeskov.anyhelper;

import android.app.Application;
import android.content.Context;

import com.yeskov.anyhelper.data.di.DataModule;
import com.yeskov.anyhelper.data.di.Scopes;

import toothpick.Toothpick;
import toothpick.configuration.Configuration;

public class App extends Application {

    private static Context appContext;
    public static App instance;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        instance = this;

        initToothpick();
        initScopes();
    }

    private void initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes());
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        }
    }

    public void initScopes() {
        Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.DATA_SCOPE)
                .installModules(new DataModule(appContext));
    }

    public static App getInstance() {
        return instance;
    }
}