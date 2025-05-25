package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {
    AsteroidPlugin newAsteroids;
    int numOfAsteroidsPresent;
    int asteroidsKilled;
    int difficultyScaling;
    public AsteroidProcessor() {
        this.newAsteroids = new AsteroidPlugin(); // Asteroid factory
        this.numOfAsteroidsPresent = 0; // Asteroids present on map
        this.asteroidsKilled = 0; // Asteroids killed in total
        this.difficultyScaling = 1;
    }
    @Override
    public void process(GameData gameData, World world) {
        this.asteroidsKilled = gameData.getAsteroidsKilled();
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            this.numOfAsteroidsPresent++; // counting.
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            // Asteroids now get destroyed when exiting the window
            float screenWidth = gameData.getDisplayWidth();
            float screenHeight = gameData.getDisplayHeight();
            if((asteroid.getX() < 0) || (asteroid.getX() > screenWidth) || (asteroid.getY() < 0) || (asteroid.getY() > screenHeight)) {
                world.removeEntity(asteroid);
                gameData.increaseAsteroidsKilled(); // added difficulty
            }
        }
        // Asteroids respawn in waves whenever the game field clears
        if (numOfAsteroidsPresent < difficultyScaling) {
            if (asteroidsKilled > 100) { // Max difficulty
                difficultyScaling = 40;
            } else if (asteroidsKilled > 50) {
                difficultyScaling = 20;
            } else if (asteroidsKilled > 20) {
                difficultyScaling = 10;
            } else if (asteroidsKilled > 5) {
                difficultyScaling = 5;
            }
            for (int i =0; i<difficultyScaling; i++){
                newAsteroids.start(gameData, world);
                gameData.increaseRounds();
            }
        }
        // Reset numOfAsteroidsPresent counting
        numOfAsteroidsPresent = 0;
    }
}
