package com.example.votedroid;

import com.example.votedroid.exceptions.*;
import com.example.votedroid.impl.ServiceImplementation;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.modele.VDQuestion;
import org.junit.Assert;
import org.junit.Test;

public class TestServiceImplementation {
    //Tests pour la création d'une question
    @Test
    public void testCréationQuestionValide() throws QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);
        Assert.assertNotNull(vdQuestion.id);
    }
    @Test(expected = QuestionInvalideLongueur.class)
    public void testCréationQuestionCourt() throws QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        vdQuestion.contenue = "Lola";
        service.ajoutQuestion(vdQuestion);
    }
    @Test(expected = QuestionInvalideLongueur.class)
    public void testCréationQuestionLong() throws QuestionInvalide,QuestionInvalideLongueur,QuestionInvalideExistante {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        vdQuestion.contenue = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        service.ajoutQuestion(vdQuestion);
    }
    @Test(expected = QuestionInvalide.class)
    public void testCréationQuestionId() throws QuestionInvalide,QuestionInvalideLongueur,QuestionInvalideExistante {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        vdQuestion.id = 3;
        service.ajoutQuestion(vdQuestion);
    }
    @Test(expected = QuestionInvalideExistante.class)
    public void testCréationQuestionDouble() throws QuestionInvalide,QuestionInvalideLongueur,QuestionInvalideExistante {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDQuestion vdQuestion2 = new VDQuestion();
        vdQuestion.contenue = "bonjour je suis une nouvelle questionn";
        vdQuestion2.contenue = "BONJOUR JE SUIS UNE NOUVELLE QUESTIONN";
        service.ajoutQuestion(vdQuestion);
        service.ajoutQuestion(vdQuestion2);

    }

    //Tests pour la création d'un vote
    @Test
    public void testCréationVoteValide() throws VoteInvalide ,QuestionInvalide, QuestionInvalideLongueur,QuestionInvalideExistante, VoteInvalideLongueur, VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);
        vdVote.idQuestion = vdQuestion.id;
        vdVote.nom = "Bob";
        vdVote.vote = 3;
        service.ajoutVote(vdVote);
        Assert.assertNotNull(vdVote);
    }
    @Test(expected = VoteInvalideExistant.class)
    public void testCréationVoteDouble() throws VoteInvalide ,QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante,VoteInvalideLongueur, VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote = new VDVote();
        VDVote vdVote2 = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);
        vdVote.idQuestion = vdQuestion.id;
        vdVote.nom = "Bob";
        vdVote.vote = 3;
        service.ajoutVote(vdVote);
        vdVote2.idQuestion = vdQuestion.id;
        vdVote2.nom = "Bob";
        vdVote2.vote = 4;
        service.ajoutVote(vdVote2);
    }
    @Test(expected = VoteInvalide.class)
    public void testCréationId() throws VoteInvalide ,QuestionInvalide, QuestionInvalideLongueur,QuestionInvalideExistante, VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);
        vdVote.idQuestion = vdQuestion.id;
        vdVote.nom = "Bob";
        vdVote.vote = 3;
        vdVote.id = String.valueOf(16);
        service.ajoutVote(vdVote);
    }
    @Test(expected = VoteInvalideLongueur.class)
    public void testCréationTropPetit() throws VoteInvalide ,QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante, VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);
        vdVote.idQuestion = vdQuestion.id;
        vdVote.nom = "Bob";
        vdVote.vote = -1;
        service.ajoutVote(vdVote);
    }
    @Test(expected = VoteInvalideLongueur.class)
    public void testCréationTropGrand() throws VoteInvalide ,QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante,VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);
        vdVote.idQuestion = vdQuestion.id;
        vdVote.nom = "Bob";
        vdVote.vote = 6;
        service.ajoutVote(vdVote);
    }
    //Tests pour le triage de questions
    @Test
    public void testTriage() throws VoteInvalide ,QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante,VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion1 = new VDQuestion();
        VDVote vdVote1 = new VDVote();
        VDQuestion vdQuestion2 = new VDQuestion();
        VDVote vdVote2 = new VDVote();
        VDQuestion vdQuestion3 = new VDQuestion();
        VDVote vdVote3 = new VDVote();
        VDQuestion vdQuestion4 = new VDQuestion();
        VDVote vdVote4 = new VDVote();
        VDQuestion vdQuestion5 = new VDQuestion();
        VDVote vdVote5 = new VDVote();
        //
        vdQuestion1.contenue = "Bonjour je suis une nouvelle question 1";
        service.ajoutQuestion(vdQuestion1);
        vdVote1.idQuestion = vdQuestion1.id;
        vdVote1.nom = "Bob";
        vdVote1.vote = 3;
        service.ajoutVote(vdVote1);
        //
        vdQuestion2.contenue = "Bonjour je suis une nouvelle question 2";
        service.ajoutQuestion(vdQuestion2);
        vdVote2.idQuestion = vdQuestion2.id;
        vdVote2.nom = "Robert";
        vdVote2.vote = 2;
        service.ajoutVote(vdVote2);
        //
        vdQuestion3.contenue = "Bonjour je suis une nouvelle question 3";
        service.ajoutQuestion(vdQuestion3);
        vdVote3.idQuestion = vdQuestion2.id;
        vdVote3.nom = "Bobi";
        vdVote3.vote = 3;
        service.ajoutVote(vdVote3);
        //
        vdQuestion4.contenue = "Bonjour je suis une nouvelle question 4";
        service.ajoutQuestion(vdQuestion4);
        vdVote4.idQuestion = vdQuestion2.id;
        vdVote4.nom = "Bobo";
        vdVote4.vote = 5;
        service.ajoutVote(vdVote4);
        //
        vdQuestion5.contenue = "Bonjour je suis une nouvelle question 5";
        service.ajoutQuestion(vdQuestion5);
        vdVote5.idQuestion = vdQuestion2.id;
        vdVote5.nom = "Bobar";
        vdVote5.vote = 1;
        service.ajoutVote(vdVote5);

        Assert.assertNotNull(service.questionsParNombreVotes());
    }
    //Tests pour la distribution
    @Test
    public void testdistribution() throws QuestionInvalide, VoteInvalide, QuestionInvalideLongueur, QuestionInvalideExistante,VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote1 = new VDVote();
        VDVote vdVote2 = new VDVote();
        VDVote vdVote3 = new VDVote();
        VDVote vdVote4 = new VDVote();
        VDVote vdVote5 = new VDVote();
        VDVote vdVote6 = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);

        vdVote1.idQuestion = vdQuestion.id;
        vdVote1.nom = "Bob";
        vdVote1.vote = 3;
        service.ajoutVote(vdVote1);
        //
        vdVote2.idQuestion = vdQuestion.id;
        vdVote2.nom = "Robert";
        vdVote2.vote = 2;
        service.ajoutVote(vdVote2);
        //
        vdVote3.idQuestion = vdQuestion.id;
        vdVote3.nom = "Bobi";
        vdVote3.vote = 3;
        service.ajoutVote(vdVote3);
        //
        vdVote4.idQuestion = vdQuestion.id;
        vdVote4.nom = "Bobo";
        vdVote4.vote = 5;
        service.ajoutVote(vdVote4);
        //
        vdVote5.idQuestion = vdQuestion.id;
        vdVote5.nom = "Bobar";
        vdVote5.vote = 1;
        service.ajoutVote(vdVote5);
        //
        vdVote6.idQuestion = vdQuestion.id;
        vdVote6.nom = "Bobarion";
        vdVote6.vote = 4;
        service.ajoutVote(vdVote6);

        Assert.assertNotNull(service.distributionPour(vdQuestion));

    }
    //Tests pour la moyenne
    @Test
    public void testMoyenne() throws QuestionInvalide, VoteInvalide, QuestionInvalideLongueur, QuestionInvalideExistante,VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote1 = new VDVote();
        VDVote vdVote2 = new VDVote();
        VDVote vdVote3 = new VDVote();
        VDVote vdVote4 = new VDVote();
        VDVote vdVote5 = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);

        vdVote1.idQuestion = vdQuestion.id;
        vdVote1.nom = "Bob";
        vdVote1.vote = 3;
        service.ajoutVote(vdVote1);
        //
        vdVote2.idQuestion = vdQuestion.id;
        vdVote2.nom = "Robert";
        vdVote2.vote = 2;
        service.ajoutVote(vdVote2);
        //
        vdVote3.idQuestion = vdQuestion.id;
        vdVote3.nom = "Bobi";
        vdVote3.vote = 3;
        service.ajoutVote(vdVote3);
        //
        vdVote4.idQuestion = vdQuestion.id;
        vdVote4.nom = "Bobo";
        vdVote4.vote = 5;
        service.ajoutVote(vdVote4);
        //
        vdVote5.idQuestion = vdQuestion.id;
        vdVote5.nom = "Bobar";
        vdVote5.vote = 1;
        service.ajoutVote(vdVote5);

        Assert.assertEquals("2.8",String.valueOf(service.moyennePour(vdQuestion)) );
    }
    //Tests pour l'écart-type
    @Test
    public void testÉcartType() throws QuestionInvalide, VoteInvalide, QuestionInvalideLongueur, QuestionInvalideExistante, VoteInvalideLongueur,VoteInvalideExistant {
        Service service = new ServiceImplementation();
        VDQuestion vdQuestion = new VDQuestion();
        VDVote vdVote1 = new VDVote();
        VDVote vdVote2 = new VDVote();
        VDVote vdVote3 = new VDVote();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        service.ajoutQuestion(vdQuestion);

        vdVote1.idQuestion = vdQuestion.id;
        vdVote1.nom = "Bob";
        vdVote1.vote = 2;
        service.ajoutVote(vdVote1);
        //
        vdVote2.idQuestion = vdQuestion.id;
        vdVote2.nom = "Robert";
        vdVote2.vote = 2;
        service.ajoutVote(vdVote2);
        //
        vdVote3.idQuestion = vdQuestion.id;
        vdVote3.nom = "Bobi";
        vdVote3.vote = 5;
        service.ajoutVote(vdVote3);

        Assert.assertEquals("1.4142135623730951", String.valueOf(service.ecartTypePour(vdQuestion)));
    }
    //Test pour NomEtudiant
    @Test
    public void testNomEtudiant() {
        Service service = new ServiceImplementation();
        Assert.assertEquals("Alex Blanchette",service.nomEtudiant());
    }
}
