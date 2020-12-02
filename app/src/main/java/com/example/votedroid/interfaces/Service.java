package com.example.votedroid.interfaces;

import com.example.votedroid.exceptions.*;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.modele.VDQuestion;

import java.util.List;
import java.util.Map;

public interface Service {

    void ajoutQuestion(VDQuestion question) throws QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante;

    void ajoutVote(VDVote vote) throws VoteInvalide, VoteInvalideLongueur, VoteInvalideExistant;

    List<VDQuestion> questionsParNombreVotes();

    Map<Integer, Integer> distributionPour(VDQuestion question);

    double moyennePour(VDQuestion question);

    double ecartTypePour(VDQuestion question);

    String nomEtudiant();
}
