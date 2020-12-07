package com.example.votedroid.modele;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = VDQuestion.class,
        parentColumns = "id",
        childColumns = "idQuestion"),
        indices = {@Index("idQuestion")}
)
public class VDVote {
    @PrimaryKey
    public Long id;
    @ColumnInfo
    public int vote;
    @ColumnInfo
    public String nom;
    @ColumnInfo
    public Long idQuestion;

    public VDVote(){

    }

    public VDVote(int vote, String nom, Long QuestionAVoter){
        this.vote = vote;
        this.nom = nom;
        this.idQuestion = QuestionAVoter;
    }
}

