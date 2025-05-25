package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.World;

import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    public AsteroidSplitterImpl(){}

    @Override
    public void createSplitAsteroid(Entity asteroidA, World world) {
        Random rnd = new Random();
        // Collision coordinates
        double x = asteroidA.getX();
        double y = asteroidA.getY();
        if (asteroidA.getRadius()>5) {
            // Asteroids of size 6-15 (default size) splits into 2-4 smaller pieces
            int numOfAsteroids = rnd.nextInt(3) + 1;
            int newSize = (int)asteroidA.getRadius()/numOfAsteroids; // This rounds down,
            if (newSize<1) newSize = 1; // so we set floor to 1
            // Direction. #1 direction is random, and the other minor asteroids have 360/numOfAsteroidsPresent angle added (180 for 2. asteroid if 2, 120 for 2. if 3 etc.)
            int direction1 = rnd.nextInt(90);
            int degreeAdded = 360/numOfAsteroids;
            for (int i = 0; i < numOfAsteroids; i++) {
                Entity asteroid = new Asteroid();
                asteroid.setPolygonCoordinates(newSize, -newSize, -newSize, -newSize, -newSize, newSize, newSize, newSize);
                asteroid.setRadius(newSize);
                asteroid.setRotation(direction1+i*degreeAdded); // i starts at 0, so first angle is equal to direction1
                asteroid.setType("Asteroid");
                asteroid.setHealth(1);
                asteroid.setX(x+rnd.nextInt(5)); //shifting a few pixels
                asteroid.setY(y+rnd.nextInt(5));

                world.addEntity(asteroid);
            }
            System.out.println("[Splitting Asteroid into smaller ones]");
        }
    }

}
