package com.example.votedroid.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.votedroid.databinding.CreateActivityBinding;
import com.example.votedroid.exceptions.QuestionInvalide;
import com.example.votedroid.exceptions.QuestionInvalideExistante;
import com.example.votedroid.exceptions.QuestionInvalideLongueur;
import com.example.votedroid.impl.ServiceImplementation;
import com.example.votedroid.interfaces.Service;
import com.example.votedroid.modele.VDQuestion;

public class CreateActivity extends AppCompatActivity {
    Service service;
    private CreateActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        service = new ServiceImplementation(getApplicationContext());

        binding.EnvoiQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateActivity.this,QuestionsActivity.class);
                VDQuestion question = new VDQuestion();
                question.contenue = String.valueOf(binding.editTextQuestion.getText());
                try {
                    service.ajoutQuestion(question);
                } catch (QuestionInvalide questionInvalide) {
                    Toast.makeText(CreateActivity.this, "La question doit pas avoir de id avant le moment de sa création",Toast.LENGTH_LONG).show();
                    questionInvalide.printStackTrace();
                } catch (QuestionInvalideLongueur questionInvalideLongueur) {
                    Toast.makeText(CreateActivity.this, "La question doit être d'une longueur entre 5 et 255 caractères",Toast.LENGTH_LONG).show();
                    questionInvalideLongueur.printStackTrace();
                } catch (QuestionInvalideExistante questionInvalideExistante) {
                    Toast.makeText(CreateActivity.this, "La question est soit non conforme ou déjà existant",Toast.LENGTH_LONG).show();
                    questionInvalideExistante.printStackTrace();
                }
                startActivity(intent);
            }
        });

    }
}