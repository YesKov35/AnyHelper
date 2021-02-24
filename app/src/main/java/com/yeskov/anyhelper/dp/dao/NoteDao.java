package com.yeskov.anyhelper.dp.dao;

import com.yeskov.anyhelper.dp.entity.NoteEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    Single<List<NoteEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntity userEntity);
}
