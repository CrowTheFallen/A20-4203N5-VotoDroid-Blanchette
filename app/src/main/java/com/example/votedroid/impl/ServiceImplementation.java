package com.example.votedroid.impl;

import com.example.votedroid.exceptions.*;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.modele.VDQuestion;

import java.text.Collator;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ServiceImplementation implements Service {
    private List<VDQuestion> questions = new ArrayList<VDQuestion>();
    private List<VDVote> votes = new ArrayList<VDVote>();
    private int idQuestionCompteur;
    private int idVoteCompteur;

    @Override
    public void ajoutQuestion(VDQuestion question) throws QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante {
        //Validation
        if(question.id != 0) throw new QuestionInvalide();
        if(question.contenue.length() < 5 || question.contenue.length() > 255) throw new QuestionInvalideLongueur();
        for (VDQuestion Q: questions) {
            if(question.contenue.toUpperCase().equals(Q.contenue.toUpperCase()) )
                throw new QuestionInvalideExistante(); }
        //Ajout
        idQuestionCompteur++;
        question.id = idQuestionCompteur;
        questions.add(question);
    }

    @Override
    public void ajoutVote(VDVote vote) throws VoteInvalide, VoteInvalideLongueur, VoteInvalideExistant {
        //Validation
        if(vote.id != null) throw new VoteInvalide();
        if(vote.vote < 0 || vote.vote > 5) throw new VoteInvalideLongueur();
        for (VDQuestion Q: questions) {
            for (VDVote V: votes){
                if(Q.id == vote.idQuestion) {
                    if (vote.nom.toUpperCase().equals(V.nom.toUpperCase()))
                        throw new VoteInvalideExistant();
                }
                
            }

        }
        //Ajout
        idVoteCompteur++;
        vote.id = "" + idVoteCompteur;
        votes.add(vote);
    }

    @Override
    public List<VDQuestion> questionsParNombreVotes() {
        List<VDQuestion> questionsParVotes = questions;
        List<Integer> nombreDeVote = new ArrayList<Integer>();
        int compteur;
        for (VDQuestion Q: questions){
            compteur = 0;
            for (VDVote V: votes) {
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
        for (VDVote V: votes) {
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
        for (VDVote V: votes)
            if(question.id == V.idQuestion)
               compteur+= V.vote;
            compteur= compteur / votes.size();
        return compteur;
    }

    @Override
    public double ecartTypePour(VDQuestion question) {
        List<Integer> liste = new ArrayList<Integer>();
        double compteur =0;
        for (VDVote V: votes)
            if(question.id == V.idQuestion){
               compteur+= V.vote;
                liste.add(V.vote);
            }
        compteur= compteur / votes.size();

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
