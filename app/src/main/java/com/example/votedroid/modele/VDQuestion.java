package com.example.votedroid.modele;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

@Entity
public class VDQuestion  {
    @PrimaryKey
    public Long id;
    @ColumnInfo
    public String contenue;

    public VDQuestion(){

    }

    public VDQuestion(String contenue){
        this.contenue = contenue;
    }

}
