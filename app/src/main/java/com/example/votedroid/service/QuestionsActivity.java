package com.example.votedroid.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.votedroid.R;
import com.example.votedroid.databinding.QuestionActivityBinding;
import com.example.votedroid.impl.ServiceImplementation;
import com.example.votedroid.interfaces.Service;


public class QuestionsActivity extends AppCompatActivity {
    Service service;
    VotesAdaptateur Adapter;
    private QuestionActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = QuestionActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        service = new ServiceImplementation(getApplicationContext());
        this.initialisationRecyclerView();
        binding.buttonAjouterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsActivity.this,CreateActivity.class);
                //intent.putExtra("id",12);
                //intent.putExtra("age", 25);
                //intent.putExtra("Cash", 200);
                startActivity(intent);
            }
        });
        Adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Supprime_votes) {

            return true;
        }
        if (id == R.id.Supprime_Question) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialisationRecyclerView(){
        RecyclerView recyclerView = binding.RecyclerQuestion;
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Adapter = new VotesAdaptateur(service.questionsParNombreVotes());
        recyclerView.setAdapter(Adapter);

    }
}