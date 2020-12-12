package com.example.votedroid.impl;

import android.content.Context;

import androidx.room.Room;

import com.example.votedroid.exceptions.QuestionInvalide;
import com.example.votedroid.exceptions.QuestionInvalideExistante;
import com.example.votedroid.exceptions.QuestionInvalideLongueur;
import com.example.votedroid.exceptions.VoteInvalide;
import com.example.votedroid.exceptions.VoteInvalideExistant;
import com.example.votedroid.exceptions.VoteInvalideLongueur;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.repo.MaBD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceImplementation implements Service {

    public MaBD maBD;

    public ServiceImplementation(Context context) {
        maBD =  Room.databaseBuilder(context, MaBD.class, "MaBD")
                .allowMainThreadQueries()
                .build();
    }



    //private List<VDQuestion> questions = maBD.dao().tousLesQuestion();
    //private List<VDVote> votes = maBD.dao().tousLesVotes();
    //private int idQuestionCompteur;
    //private int idVoteCompteur;

    @Override
    public void ajoutQuestion(VDQuestion question) throws QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante {
        //Validation
        if(question.id != null) throw new QuestionInvalide();
        if(question.contenue.length() < 5 || question.contenue.length() > 255) throw new QuestionInvalideLongueur();
        // possible méthode DAO aller chercher une questions par son contenu
        // ou alors si je me souviens viens dao.findAll
        for (VDQuestion Q: this.maBD.dao().tousLesQuestion()) {
            if(question.contenue.toUpperCase().equals(Q.contenue.toUpperCase()) )
                throw new QuestionInvalideExistante(); }
        //Ajout
        //idQuestionCompteur++;
        //question.id = Long.valueOf(idQuestionCompteur);
        // et maintenant que tout est beau écrire dans la BD
        // DAO > crééer question
        //questions.add(question);
        this.maBD.dao().creerQuestion(question);
    }

    @Override
    public void ajoutVote(VDVote vote) throws VoteInvalide, VoteInvalideLongueur, VoteInvalideExistant {
        //Validation
        if(vote.id != null) throw new VoteInvalide();
        if(vote.vote < 0 || vote.vote > 5) throw new VoteInvalideLongueur();
        // DAO aller chercher un vote selons la question et la personne
        // SELECT FROM VDVOTE were questionID = vote.questionID and voteur = vote.voteur
        for (VDQuestion Q: this.maBD.dao().tousLesQuestion()) {
            for (VDVote V: this.maBD.dao().tousLesVotes()){
                if(Q.id == vote.idQuestion) {
                    if (vote.nom.toUpperCase().equals(V.nom.toUpperCase()))
                        throw new VoteInvalideExistant();
                }
                
            }

        }
        //Ajout
        //idVoteCompteur++;
        //vote.id = Long.valueOf(idVoteCompteur);
        // DAO > crééer vote

        //votes.add(vote);
        this.maBD.dao().creerVote(vote);
    }

    @Override
    public List<VDQuestion> questionsParNombreVotes() {
        List<VDQuestion> questionsParVotes = maBD.dao().tousLesQuestion();
        List<Integer> nombreDeVote = new ArrayList<Integer>();
        int compteur;
        for (VDQuestion Q: maBD.dao().tousLesQuestion()){
            compteur = 0;
            for (VDVote V: maBD.dao().tousLesVotes()) {
                if(Q.id == V.idQuestion)
                    compteur++;
            }
            nombreDeVote.add(compteur);
        }
        for (int i = 0; i < nombreDeVote.size() -1; i++) {
            for (int j = 1 + i; j < nombreDeVote.size(); j++) {
                if(nombreDeVote.get(i) < nombreDeVote.get(j)){
                    Collections.swap(questionsParVotes,j,i);
                }
                else if(nombreDeVote.get(i) == nombreDeVote.get(j)){
                    if(questionsParVotes.get(j).contenue.compareTo(questionsParVotes.get(i).contenue) > 0)
                        Collections.swap(questionsParVotes,j,i);
                }
            }
        }
        return questionsParVotes;
    }

    @Override
    public Map<Integer, Integer> distributionPour(VDQuestion question) {
        Map<Integer, Integer> distribution = new HashMap<Integer, Integer>();
        int compteur0 =0;
        int compteur1 =0;
        int compteur2 =0;
        int compteur3 =0;
        int compteur4 =0;
        int compteur5 =0;
        for (VDVote V: maBD.dao().tousLesVotes()) {
            if(question.id == V.idQuestion)
                switch (V.vote){
                case 0: compteur0++;
                break;
                case 1: compteur1++;
                    break;
                case 2: compteur2++;
                    break;
                case 3: compteur3++;
                    break;
                case 4: compteur4++;
                    break;
                case 5: compteur5++;
                    break;
            }
        }
        distribution.put(0,compteur0);
        distribution.put(1,compteur1);
        distribution.put(2,compteur2);
        distribution.put(3,compteur3);
        distribution.put(4,compteur4);
        distribution.put(5,compteur5);
        return distribution;
    }

    @Override
    public double moyennePour(VDQuestion question) {
        double compteur =0;
        for (VDVote V: maBD.dao().tousLesVotes())
            if(question.id == V.idQuestion)
               compteur+= V.vote;
            compteur= compteur / maBD.dao().tousLesVotes().size();
        return compteur;
    }

    @Override
    public double ecartTypePour(VDQuestion question) {
        List<Integer> liste = new ArrayList<Integer>();
        double compteur =0;
        for (VDVote V: maBD.dao().tousLesVotes())
            if(question.id == V.idQuestion){
               compteur+= V.vote;
                liste.add(V.vote);
            }
        compteur= compteur / maBD.dao().tousLesVotes().size();

        double standardDeviation = 0.0;

        double moyenne = compteur;
        for(double num: liste) {
            standardDeviation += Math.pow(num - moyenne, 2);
        }
        return Math.sqrt(standardDeviation/liste.size());
    }

    @Override
    public String nomEtudiant() {
        return "Alex Blanchette";
    }
}
