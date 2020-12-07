package com.example.votedroid.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.votedroid.R;
import com.example.votedroid.repo.MaBD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaBD bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "MaBD")
                .allowMainThreadQueries()
                .build();
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
}