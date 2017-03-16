package com.simulando.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Luciano José on 12/03/2017.
 */

public class Ranking {

    public ArrayList<RankingRow> rows;

    public Ranking() {
        this.rows = new ArrayList<>();
    }
}
