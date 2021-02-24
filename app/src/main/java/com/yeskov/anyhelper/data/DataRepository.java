package com.yeskov.anyhelper.data;

import com.yeskov.anyhelper.App;
import com.yeskov.anyhelper.dp.AppDatabase;

public class DataRepository {

    private final Prefs prefs;

    private AppDatabase db;

    public DataRepository(Prefs prefs) {
        this.prefs = prefs;

        db = App.getInstance().getDatabase();
    }

    public AppDatabase getDb() {
        return db;
    }
}
