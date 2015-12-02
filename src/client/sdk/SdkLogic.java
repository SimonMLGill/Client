package client.sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Simon DonGill on 12-11-2015.
 */
public class SdkLogic {

    ServerConnection serverConnection = new ServerConnection();




    // login method using a user-object and returns a message of whether or not the said users login is successful.

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
                user.setUserId((int)(long) jsonObject.get("userid"));

            return(String) jsonObject.get("message");
        }

        return "";
    }

    // tablemodel-method which returns the highscores of the games

    public ArrayList<User> getHighscores(){

        /*ArrayList<String> list = new ArrayList<String>();
        JSONArray jarray = new JSONArray();
        try {
            if (jarray != null) {
                int length = jarray.length();
                for (int i = 0; i < length; i++) {
                    list.add(jarray.get(i).toString());
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        }*/

        String data = serverConnection.get("api/scores/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<User>>(){}. getType());
    }

    // tablemodel-method which returns the available/open games

    public ArrayList<Game> openGames() {

         /*DefaultTableModel dm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };*/

        String data = serverConnection.get("games/open/");

        //JSON.stringify(data);

        return new Gson().fromJson(data, new TypeToken<ArrayList<Game>>(){}.getType());
    }

    // method that gets a user via a userid

    public static void getUser(int userId){
        ServerConnection sc = new ServerConnection();

        User user = new User();
        user.setUserId(userId);

    }
    public static void getGame(int gameId){

    }

    // method that enables a player to join a game, which is detemined by a gameid,
    // and furthermore depends on the opposing user and said users controls

    public static void joinGame(int gameId, Gamer opponent, String controls){
        ServerConnection sc = new ServerConnection();

        Game game = new Game();
        game.setGameId(gameId);
        game.setOpponent(opponent);
        game.getOpponent().setControls(controls);

        String json = new Gson().toJson(game);

        sc.post(json, "api/games/join/");

    }

    // method which effectively runs/starts a game detemined by a gameid,
    // and runs the controls of both users

    public static void startGame(int gameId){
        ServerConnection sc = new ServerConnection();

        Game game = new Game();
        game.setGameId(gameId);

        String json = new Gson().toJson(game);

        sc.post(json, "api/games/start/");
    }

    // method that creates a game

    public String createGame(Game game){

        String data = serverConnection.post(new Gson().toJson(game), "games/");

        HashMap<String, String>hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");

    }

    public String deleteGame(int gameId){
        String data = serverConnection.delete("/games/" + gameId);
        return data;
    }
}
