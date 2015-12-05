package client.sdk;

/**
 * Created by Simon DonGill on 04-12-2015.
 */
public class Score {

    /**
     * creating variables that define a score
     */

    private int id;
    private Gamer user;
    private Gamer opponent;
    private Game game;
    private int score;

    /**
     * Creates constructor of the score
     * @param id of the game
     * @param user the user of the score
     * @param game the game the scores was set in
     * @param opponent the opponent the score was set against
     * @param score the score
     */
    public Score(int id, Gamer user, Game game, Gamer opponent, int score)
    {
        this.id = id;
        this.user = user;
        this.game = game;
        this.opponent = opponent;
        this.score = score;
    }

    public Score(){}

    /**
     * Creates get and set method so the variables can be accessed and used by other classes
     * @return
     */

    public Gamer getOpponent() { return opponent; }

    public void setOpponent(Gamer opponent) { this.opponent = opponent;  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gamer getUser() {
        return user;
    }

    public void setUser(Gamer user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
