package dk.sdu.cbse.common;
import org.springframework.web.client.RestTemplate;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int asteroidsKilled;
    private int enemiesKilled;
    private int playerDeaths;
    private int rounds;

    private RestTemplate restTemplate;

    public GameData(){} // Default constructor for testing purposes
    public GameData(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getAsteroidsKilled() {
        return asteroidsKilled;
    }
    public void increaseAsteroidsKilled() {
        asteroidsKilled++;
        String points = String.valueOf(1);
        if (restTemplate != null) {
            restTemplate.getForObject("http://localhost:8080/addScore?point="+points, Void.class);
        }
    }
    public int getEnemiesKilled() {
        return enemiesKilled;
    }
    public void increaseEnemiesKilled() {
        enemiesKilled++;
        String points = String.valueOf(10);
        if (restTemplate != null) {
            restTemplate.getForObject("http://localhost:8080/addScore?point="+points, Void.class);
        }
    }
    public int getPlayerDeaths() {
        return playerDeaths;
    }
    public void increasePlayerDeaths() {
        playerDeaths++;
        String points = String.valueOf(-10);
        if (restTemplate != null) {
            restTemplate.getForObject("http://localhost:8080/addScore?point="+points, Void.class);
        }
    }
    public int getRounds() {
        return rounds;
    }
    public void increaseRounds() {
        rounds++;
        String points = String.valueOf(20);
        if (restTemplate != null) {
            restTemplate.getForObject("http://localhost:8080/addScore?point="+points, Void.class);
        }
    }
    public int getCurrentScore() {
        if (restTemplate != null) {
            return restTemplate.getForObject("http://localhost:8080/getScore", Integer.class);
        }
        return 0; // Default score if RestTemplate is not available
    }
}
