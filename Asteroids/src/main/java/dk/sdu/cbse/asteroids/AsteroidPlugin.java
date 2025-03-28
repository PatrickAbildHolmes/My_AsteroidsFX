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
        System.out.println("Spawning Asteroid" + asteroid);
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
        int size = rnd.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        // Spawn location
        int rndx = rnd.nextInt(100) + 1;
        int rndy = rnd.nextInt(100) + 1;
        asteroid.setX(rndx);
        asteroid.setY(rndy);
        return asteroid;
    }
}
