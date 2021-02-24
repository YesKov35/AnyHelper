package com.yeskov.anyhelper.adapters;

import com.yeskov.anyhelper.dp.entity.NoteEntity;
import com.yeskov.anyhelper.dp.entity.ToolEntity;

public class RecyclerModel {

    private int type;

    private ToolEntity toolEntity;
    private NoteEntity noteEntity;

    public RecyclerModel(int type){
        this.type = type;
    }

    public RecyclerModel(int type, ToolEntity toolEntity){
        this.type = type;
        this.toolEntity = toolEntity;
    }

    public RecyclerModel(int type, NoteEntity noteEntity){
        this.type = type;
        this.noteEntity = noteEntity;
    }

    public int getType() {
        return type;
    }

    public ToolEntity getToolEntity() {
        return toolEntity;
    }

    public NoteEntity getNoteEntity() {
        return noteEntity;
    }
}
