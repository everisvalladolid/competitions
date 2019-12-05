package com.everis.competition.prisoner;

import java.util.ArrayList;

public interface IPrisoner {

    public int decide(ArrayList<Decision> decisions);

    public String getName();
}
