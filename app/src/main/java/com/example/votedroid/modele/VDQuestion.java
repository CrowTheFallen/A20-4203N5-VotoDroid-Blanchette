package com.example.votedroid.modele;

import java.util.List;
import java.util.Objects;

public class VDQuestion extends VDVote {
    public String contenue;
    public int id;

    public VDQuestion(){

    }

    public VDQuestion(String contenue){
        this.contenue = contenue;
    }

}
