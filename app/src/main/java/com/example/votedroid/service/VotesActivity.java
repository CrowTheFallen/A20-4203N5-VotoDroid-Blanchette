package com.example.votedroid.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.votedroid.R;
import com.example.votedroid.databinding.QuestionActivityBinding;
import com.example.votedroid.databinding.VotesActivityBinding;

public class VotesActivity extends AppCompatActivity {
    private VotesActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VotesActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.LaQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VotesActivity.this,StatsActivity.class);
                //intent.putExtra("id",12);
                //intent.putExtra("age", 25);
                //intent.putExtra("Cash", 200);
                startActivity(intent);
            }
        });

    }
}