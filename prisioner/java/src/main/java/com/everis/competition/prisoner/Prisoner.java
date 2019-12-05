package com.everis.competition.prisoner;

import java.util.ArrayList;

public abstract class Prisoner implements IPrisoner {

    private String name;

    public String getName() {
        return name;
    }

    public Prisoner(String name) {
        this.name = name;
    }

    public abstract int decide(ArrayList<Decision> decisions);

}
