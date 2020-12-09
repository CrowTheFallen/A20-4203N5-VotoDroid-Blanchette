package com.example.votedroid.service;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.votedroid.R;
import com.example.votedroid.databinding.CreateActivityBinding;
import com.example.votedroid.databinding.QuestionActivityBinding;

public class CreateActivity extends AppCompatActivity {
    private CreateActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}