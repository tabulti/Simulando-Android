package com.simulando.Models;

/**
 * Created by Luciano José on 12/02/2017.
 */

public class RankItem {

    public int position;
    public String picture_url;
    public String profile_name;
    public String score;

    public RankItem(int position, String picture_url, String profile_name, String score) {
        this.position = position;
        this.picture_url = picture_url;
        this.profile_name = profile_name;
        this.score = score;
    }

    public int getPosition() {
        return position;
    }
}
