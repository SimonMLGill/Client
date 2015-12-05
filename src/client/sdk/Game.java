package client.sdk;
import java.util.Date;

/**
 * Created by Simon DonGill on 12-11-2015.
 */
public class Game {

    /**
     * creating variables that define a game
     */

    private int gameId;
    private Gamer winner;
    private String name;
    private Gamer host;
    private Gamer opponent;
    private String status;
    private java.sql.Date created;
    private int mapSize;

    public Game(){}

    /**
     * get and set methods so the variables can be accessed and used by other classes
     * @return
     */

    public java.sql.Date getCreated() {
        return created;
    }

    public void setCreated(java.sql.Date created) {
        this.created = created;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Creating get/set method for all the variables, so they can be used by other classes
    public int getGameId(){
        return gameId;
    }

    public void setGameId(int gameId){
        this.gameId = gameId;
    }

    public Gamer getHost(){
        return host;
    }

    public void setHost(Gamer host){
        this.host = host;
    }

    public Gamer getOpponent(){
        return opponent;
    }

    public void setOpponent(Gamer opponent){
        this.opponent = opponent;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Gamer getWinner() {
        return winner;
    }

    public void setWinner(Gamer winner) {
        this.winner = winner;
    }
}
