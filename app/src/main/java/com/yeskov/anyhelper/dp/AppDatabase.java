package com.yeskov.anyhelper.dp;

import com.yeskov.anyhelper.dp.dao.NoteDao;
import com.yeskov.anyhelper.dp.entity.NoteEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
