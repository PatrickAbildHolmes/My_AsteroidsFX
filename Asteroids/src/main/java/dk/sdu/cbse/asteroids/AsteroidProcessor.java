package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
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
        // Block for respawning asteroids if 1 or less are present (so there are always at least 2)
        if (numOfAsteroidsPresent < difficultyScaling) {
            if (asteroidsKilled > 20) {
                difficultyScaling = 50; // Min. 10 asteroids at all times
//                for (int i =0; i<difficultyScaling; i++){
//                    newAsteroids.start(gameData, world);
//                }
            } else if (asteroidsKilled > 10) {
                difficultyScaling = 25; // 5 asteroids at all times
//                for (int i =0; i<difficultyScaling; i++){
//                    newAsteroids.start(gameData, world);
//                }
            } else if (asteroidsKilled > 5) {
                difficultyScaling = 10;
//                for (int i =0; i<difficultyScaling; i++){
//                    newAsteroids.start(gameData, world);
//                }
            } else if (asteroidsKilled > 2) {
                difficultyScaling = 5;
//                for (int i =0; i<difficultyScaling; i++){
//                    newAsteroids.start(gameData, world);
//                }
            }
//            else { // 0 killed
//                newAsteroids.start(gameData, world);
//            }
            for (int i =0; i<difficultyScaling; i++){
                newAsteroids.start(gameData, world);
            }
        }
        // Reset numOfAsteroidsPresent counting
        numOfAsteroidsPresent = 0;
    }
}
