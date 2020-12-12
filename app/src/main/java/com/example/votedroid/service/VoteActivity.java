package com.example.votedroid.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.votedroid.databinding.VoteActivityBinding;
import com.example.votedroid.exceptions.VoteInvalide;
import com.example.votedroid.exceptions.VoteInvalideExistant;
import com.example.votedroid.exceptions.VoteInvalideLongueur;
import com.example.votedroid.impl.ServiceImplementation;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;
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
                    Toast.makeText(VoteActivity.this, "Le ID du vote ne peut pas être attribuer avant le vote",Toast.LENGTH_LONG).show();
                    voteInvalide.printStackTrace();
                } catch (VoteInvalideLongueur voteInvalideLongueur) {
                    Toast.makeText(VoteActivity.this, "Le vote doit être entre 0 et 5 étoiles",Toast.LENGTH_LONG).show();
                    voteInvalideLongueur.printStackTrace();
                } catch (VoteInvalideExistant voteInvalideExistant) {
                    Toast.makeText(VoteActivity.this, "Le nom de votant existe déjà sur cette question",Toast.LENGTH_LONG).show();
                    voteInvalideExistant.printStackTrace();
                }


                startActivity(intent);
            }
        });


    }
}
