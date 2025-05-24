package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
        System.out.println("Spawning Asteroid" + asteroid.getID());
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        System.out.println("Creating Asteroid");
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        // Asteroid size
        int size = rnd.nextInt(10) + 5; // Size from 6 to 15
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setType("Asteroid");
        asteroid.setHealth(1);

        // Random spawn location along Y-axis
        int randomY = rnd.nextInt(gameData.getDisplayHeight());
        asteroid.setY(randomY);
        asteroid.setX(0);
        return asteroid;
    }
}
