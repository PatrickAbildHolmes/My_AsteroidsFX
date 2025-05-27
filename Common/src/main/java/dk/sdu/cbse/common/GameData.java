package dk.sdu.cbse.common;
import java.io.*;
import java.net.*;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int asteroidsKilled;
    private int enemiesKilled;
    private int playerDeaths;
    private int rounds;
    private final String baseURL;

    public GameData() {
        this.baseURL = "http://localhost:8080";
    }
    public void increaseAsteroidsKilled() throws IOException {
        asteroidsKilled++;
        // a "PUT" request to http://localhost:8080/addScore?point=10 adds 10 points
        try {addScore(1);}
        catch (Exception e) {e.printStackTrace();}
    }
    public void increaseEnemiesKilled() throws IOException {
        enemiesKilled++;
        try {addScore(10);}
        catch (Exception e) {e.printStackTrace();}
    }
    public void increasePlayerDeaths() throws IOException {
        playerDeaths++;
        try {addScore(-10);}
        catch (Exception e) {e.printStackTrace();}
    }
    public void increaseRounds() throws IOException {
        rounds++;
        try {addScore(10);}
        catch (Exception e) {e.printStackTrace();}
    }
    public int getCurrentScore() throws IOException { // Called from Game.java
        String endpoint = "/getScore";
        URL url = new URL(this.baseURL+endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return Integer.parseInt(connect(connection,"GET"));
    }

    public int getRounds() {return rounds;}
    public int getAsteroidsKilled() {return asteroidsKilled;}
    public int getPlayerDeaths() {return playerDeaths;}
    public int getEnemiesKilled() {return enemiesKilled;}
    public GameKeys getKeys() {return keys;}
    public void setDisplayWidth(int width) {this.displayWidth = width;}
    public int getDisplayWidth() {return displayWidth;}
    public void setDisplayHeight(int height) {this.displayHeight = height;}
    public int getDisplayHeight() {return displayHeight;}

    public void addScore(int addPoints) throws IOException {
        String endpoint = "/addScore?points=" + addPoints;
        URL url = new URL(this.baseURL+endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connect(connection,"PUT");
    }

    // Helper-method. Made using https://www.baeldung.com/java-http-request
    public String connect(HttpURLConnection connection, String requestType) throws IOException {
        // Setting up connection
        connection.setRequestMethod(requestType);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        // Reading the return value
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return content.toString();
    }
}