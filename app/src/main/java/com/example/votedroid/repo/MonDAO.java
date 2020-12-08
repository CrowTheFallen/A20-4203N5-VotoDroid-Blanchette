package com.example.votedroid.repo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;

import java.util.List;

@Dao
public abstract class MonDAO {

    @Insert
    public abstract Long creerQuestion(VDQuestion vdQuestion);

    @Insert
    public abstract Long creerVote(VDVote vdVote);

    //@Query("SELECT * FROM VDQuestion where lowercase(contenue) = lowercase(:param)")
    //public abstract List<VDQuestion> tousLesQuestion(String param);



    @Query("SELECT * FROM VDQuestion")
    public abstract List<VDQuestion> tousLesQuestion();

    @Query("SELECT * FROM VDVote")
    public abstract List<VDVote> tousLesVotes();

    @Transaction
    public Long creerQuestionVotes(VDQuestion a, List<VDVote> ps){
        Long Id = this.creerQuestion(a);
        for (VDVote p : ps){
           p.idQuestion = Id;
            this.creerVote(p);
        }
        return Id;
    }
    @Transaction
    public Long creerQuestionVote(VDQuestion a, VDVote ps){
        Long Id = this.creerQuestion(a);
        ps.idQuestion = Id;
        this.creerVote(ps);
        return Id;
    }

}
