package client.sdk;

/**
 * Created by Simon DonGill on 26-11-2015.
 */
public class Gamer extends User {

    /**
     * creating variables that define a Game
     */

    private int endScore;
    private int score;
    private int kills;
    private String controls;
    private boolean champion;

    /**
     * creating get and set for the variables so the other classes can access and use the said variables
     * @return
     */

    public boolean isChampion(boolean champion){
        return champion;
    }

    public void setChampion(boolean champion){
        this.champion = champion;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setEndScore(int endScore){
        this.endScore = endScore;
    }

    public void setKills(int kills){
        this.kills = kills;
    }

    public void setControls(String controls){
        this.controls = controls;
    }

    public int getScore(){
        return score;
    }

    public int getEndScore(){
        return endScore;
    }

    public int getKills(){
        return kills;
    }

    public String getControls(){
        return controls;
    }


}

