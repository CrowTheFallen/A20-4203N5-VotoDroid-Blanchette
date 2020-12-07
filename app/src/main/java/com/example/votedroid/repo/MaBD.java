package com.example.votedroid.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;

@Database(entities = {VDQuestion.class, VDVote.class}, version = 2)
public abstract class MaBD extends RoomDatabase{

    public abstract MonDAO dao();
}
