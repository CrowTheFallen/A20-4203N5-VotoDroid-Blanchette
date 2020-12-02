package com.example.votedroid.modele;

public class VDVote {
    public int vote;
    public String id;
    public String nom;
    public int idQuestion;

    public VDVote(){

    }

    public VDVote(int vote, String nom, int QuestionAVoter){
        this.vote = vote;
        this.nom = nom;
        this.idQuestion = QuestionAVoter;
    }
}

