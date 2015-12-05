package client.sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Simon DonGill on 12-11-2015.
 */
public class SdkLogic {

    ServerConnection serverConnection = new ServerConnection();

    /**
     * login method using a user-object and returns a message of whether or not the said users login is successful.
     * @param user the user object that is to be logged in
     * @return
     */

    public String login(User user){

        String data = serverConnection.post(new Gson().toJson(user), "login/");

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = null;
        try {
            Object object = parser.parse(data);
            jsonObject = (JSONObject) object;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {

            if (jsonObject.get("userid") != null)
                user.setId((int)(long) jsonObject.get("userid"));

            return(String) jsonObject.get("message");
        }

        return "";
    }

    /**
     * tablemodel-method which returns the highscores of the games
     * @return
     */

    public ArrayList<Score> getHighscores() {

        String data = serverConnection.get("highscores/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<Score>>(){}. getType());
    }

    /**
     * tablemodel-method which returns the available/open games
     * @return
     */

    public ArrayList<Game> openGames() {

        String data = serverConnection.get("games/open/");


        return new Gson().fromJson(data, new TypeToken<ArrayList<Game>>(){}.getType());
    }

    /**
     * method that returns a ArrayList og user objects
     * @return
     */

    public ArrayList<User> getUsers(){
        String data = serverConnection.get("users/");

        return new Gson().fromJson(data, new TypeToken<ArrayList<User>>(){}.getType());
    }

    /**
     * method that gets open games via a userid
     * @param userId the id of the user
     * @return
     */

    public ArrayList<Game> getGamesById(int userId){
        String data = serverConnection.get("games/open/" + userId);
        return new Gson().fromJson(data, new TypeToken<ArrayList<Game>>() {
        }.getType());
    }

    /**
     * method that enables a player to join a game, which is detemined by a gameid,
     * and furthermore depends on the opposing user and said users controls
     * @param joinGame the game that is joined
     * @return
     */

    public String joinGame(Game joinGame){

        //Game game = new Game();
        //game.setGameId(gameId);
        //game.setOpponent(opponent);
        //game.getOpponent().setControls(controls);

       // String json = new Gson().toJson(game);

        String data = serverConnection.put(new Gson().toJson(joinGame), "games/join/");

        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }

    /**
     * method which effectively runs/starts a game detemined by a gameid,
     * and runs the controls of both users
     * @param sGame the game to be started
     * @return
     */

    public String startGame(Game sGame){

        String data = serverConnection.put(new Gson().toJson(sGame), "games/start/");

        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        if (hashMap.get("message") != null)
            return hashMap.get("message");
        else {
            Game gm = new Gson().fromJson(data, Game.class);
            sGame.setWinner(gm.getWinner());
            System.out.println(gm.getName());
            return sGame.getWinner().getId()+ "";
        }
    }

    /**
     * method that creates a game
     * @param game the game object that is created
     * @return
     */

    public String createGame(Game game){

        String data = serverConnection.post(new Gson().toJson(game), "games/");

        HashMap<String, String>hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");

    }

    /**
     * method that deletes a game
     * @param gameId the game id for the game to be deleted
     * @return
     */

    public String deleteGame(int gameId){
        String data = serverConnection.delete("games/" + gameId);
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }
}
