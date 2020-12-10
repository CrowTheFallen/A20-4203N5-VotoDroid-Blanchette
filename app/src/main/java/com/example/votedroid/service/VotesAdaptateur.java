package com.example.votedroid.service;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.votedroid.R;
import com.example.votedroid.modele.VDQuestion;
import com.example.votedroid.modele.VDVote;
import com.example.votedroid.repo.MaBD;

import java.util.ArrayList;
import java.util.List;

public class VotesAdaptateur extends RecyclerView.Adapter<VotesAdaptateur.MyViewHolder> {


    public List<VDQuestion> list;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question;


        public MyViewHolder(LinearLayout view) {
            super(view);
            // Define click listener for the ViewHolder's View
            question = view.findViewById(R.id.LaQuestion);
        }

    }

    public VotesAdaptateur(List<VDQuestion> listDeQuestion) {
        list = listDeQuestion;
    }

    // Create new views (invoked by the layout manager)


    @NonNull
    @Override
    public VotesAdaptateur.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.votes_activity, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        Log.i("DEBUGAGE", "Appel de onCreateViewHolder");
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        VDQuestion questionCourante = list.get(position);
        holder.question.setText(questionCourante.contenue);
        Log.i("DEBUGAGE", "Appel de onBindViewHolder" + position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}
