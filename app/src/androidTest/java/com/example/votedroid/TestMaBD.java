package com.example.votedroid;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.votedroid.exceptions.QuestionInvalide;
import com.example.votedroid.exceptions.QuestionInvalideExistante;
import com.example.votedroid.exceptions.QuestionInvalideLongueur;
import com.example.votedroid.exceptions.VoteInvalide;
import com.example.votedroid.impl.ServiceImplementation;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.repo.MaBD;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(AndroidJUnit4.class)
public class TestMaBD {

    @Test
    public void testCréationQuestionValide() throws QuestionInvalide, QuestionInvalideLongueur, QuestionInvalideExistante {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();


        VDQuestion vdQuestion = new VDQuestion();
        vdQuestion.contenue = "Bonjour je suis une nouvelle question";
        //service.ajoutQuestion(vdQuestion);
       // Assert.assertNotNull(vdQuestion.id);

        bd.close();
    }

    @Test
    public void test10Question() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();

        for (int i = 0 ; i < 10 ; i++ ) {
            VDQuestion a = new VDQuestion();
            a.contenue = "salut " + i;
            bd.dao().creerQuestion(a);
        }
        List<VDQuestion> Questions = bd.dao().tousLesQuestion();
        assertEquals(10, Questions.size());
        bd.close();
    }
    @Test
    public void test100Questions() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();

        for (int i = 0 ; i < 100 ; i++ ) {
            VDQuestion a = new VDQuestion();
            a.contenue = "salut " + i;
            bd.dao().creerQuestion(a);
        }
        List<VDQuestion> Questions = bd.dao().tousLesQuestion();
        assertEquals(100, Questions.size());
        bd.close();
    }
    @Test
    public void testVote() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();

        VDQuestion a = new VDQuestion();
        a.contenue = "salut ";
        bd.dao().creerQuestion(a);

        VDVote b = new VDVote(3,"Bob",a.id);
        bd.dao().creerVote(b);

        List<VDVote> Vote = bd.dao().tousLesVotes();

        assertEquals(1, Vote.size());
        bd.close();
    }
    @Test
    public void testVote10() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();

        VDQuestion a = new VDQuestion();
        a.contenue = "salut ";

        bd.dao().creerQuestion(a);
        for (int i =0;i<10;i++){
            VDVote b = new VDVote();
            b.nom = "Bob " + i;
            b.vote = 3;
            b.idQuestion = a.id;
            bd.dao().creerVote(b);
        }

        List<VDVote> Vote = bd.dao().tousLesVotes();
        assertEquals(10, Vote.size());
        bd.close();
    }
    @Test
    public void testTransactionPlusieursVotes() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "transaction").build();
        VDQuestion a = new VDQuestion();
        a.contenue = "Bonjour, je suis une question utile";
        List<VDVote> voteListe = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            VDVote votes = new VDVote(4,"Bob "+i,a.id);
            voteListe.add(votes);
        }

        bd.dao().creerQuestionVotes(a, voteListe);

        List<VDQuestion> albums = bd.dao().tousLesQuestion();
        assertEquals(1, albums.size());
        bd.close();
    }
    @Test
    public void testTransactionUnSeulVote() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "transaction").build();
        VDQuestion a = new VDQuestion();
        a.contenue = "Bonjour, je suis une question utile";

        List<VDVote> voteListe = new ArrayList<>();
        VDVote votes = new VDVote(4,"Bob",a.id);
        voteListe.add(votes);


        bd.dao().creerQuestionVotes(a, voteListe);

        List<VDQuestion> albums = bd.dao().tousLesQuestion();
        assertEquals(1, albums.size());
        bd.close();
    }

}
