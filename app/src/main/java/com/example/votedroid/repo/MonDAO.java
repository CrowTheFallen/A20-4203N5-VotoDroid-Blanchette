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

    @Query("DELETE FROM VDVote")
    public abstract void supprimeLesVotes();

    //@Query("SELECT * FROM VDVote join VDQuestion where VDQuestion.id = VDVote.idQuestion"; "DELETE"   SELECT VDVote.idQuestion FROM VDVote join VDQuestion where VDQuestion.id != VDVote.idQuestion )
    //(SELECT VDVote.idQuestion FROM VDVote inner join VDQuestion on VDQuestion.id = VDVote.idQuestion)
    //SELECT VDVote.idQuestion FROM VDVote join VDQuestion on VDQuestion.id == VDVote.idQuestion
    @Query("DELETE FROM VDQuestion")
    public abstract void supprimeLesQuestionOuAucunVote();

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

        ps.idQuestion = a.id;

        return ps.idQuestion;
    }

}
