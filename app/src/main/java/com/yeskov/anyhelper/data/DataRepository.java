package com.yeskov.anyhelper.data;

import com.yeskov.anyhelper.App;
import com.yeskov.anyhelper.dp.AppDatabase;
import com.yeskov.anyhelper.dp.entity.NoteEntity;

public class DataRepository {

    private final Prefs prefs;

    private AppDatabase db;

    private NoteEntity noteEntity;

    public DataRepository(Prefs prefs) {
        this.prefs = prefs;

        db = App.getInstance().getDatabase();
    }

    public AppDatabase getDb() {
        return db;
    }

    public NoteEntity getNoteEntity() {
        return noteEntity;
    }

    public void setNoteEntity(NoteEntity noteEntity) {
        this.noteEntity = noteEntity;
    }
}
