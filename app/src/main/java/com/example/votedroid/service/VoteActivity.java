package com.example.votedroid.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;

import com.example.votedroid.R;
import com.example.votedroid.databinding.QuestionActivityBinding;
import com.example.votedroid.databinding.VoteActivityBinding;
import com.example.votedroid.exceptions.VoteInvalide;
import com.example.votedroid.exceptions.VoteInvalideExistant;
import com.example.votedroid.exceptions.VoteInvalideLongueur;
import com.example.votedroid.impl.ServiceImplementation;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.repo.MaBD;
import com.example.votedroid.repo.MonDAO;

public class VoteActivity extends AppCompatActivity {
    Service service;
    MonDAO monDAO;
    private VoteActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VoteActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        service = new ServiceImplementation(getApplicationContext());



        final String ContenueQuestion = getIntent().getStringExtra("Question");
        binding.TextQuestion.setText(ContenueQuestion);

        binding.buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoteActivity.this,QuestionsActivity.class);
                VDVote vote = new VDVote();
                vote.nom = String.valueOf(binding.editTextTextPersonName.getText());
                vote.vote = binding.ratingBarStar.getProgress();

                for (VDQuestion Q : service.questionsParNombreVotes())
                    if( ContenueQuestion.equals(Q.contenue))
                        vote.idQuestion = Q.id;

                try {
                    service.ajoutVote(vote);
                } catch (VoteInvalide voteInvalide) {
                    voteInvalide.printStackTrace();
                } catch (VoteInvalideLongueur voteInvalideLongueur) {
                    voteInvalideLongueur.printStackTrace();
                } catch (VoteInvalideExistant voteInvalideExistant) {
                    voteInvalideExistant.printStackTrace();
                }


                startActivity(intent);
            }
        });


    }
}
