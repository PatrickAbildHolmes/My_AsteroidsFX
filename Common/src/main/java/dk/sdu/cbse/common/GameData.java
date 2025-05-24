package dk.sdu.cbse.common;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int asteroidsKilled;
    private int enemiesKilled;
    private int playerDeaths;

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
    }
    public int getEnemiesKilled() {
        return enemiesKilled;
    }
    public void increaseEnemiesKilled() {
        enemiesKilled++;
    }
    public int getPlayerDeaths() {
        return playerDeaths;
    }
    public void increasePlayerDeaths() {
        playerDeaths++;
    }
}
