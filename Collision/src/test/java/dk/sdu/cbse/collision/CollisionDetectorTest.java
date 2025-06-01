package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 9 Tests.<br>
 * 3 tests that test whether the .collides() method works correctly (I.E. collision is detected correctly).<br>
 * 6 tests that should assert True by correctly simulating how a collision between Entities of the respective types should behave.<br>
 */
public class CollisionDetectorTest {
    CollisionDetector collisionDetector = new CollisionDetector();
    GameData gameData = new GameData();
    World world = new World();

    // Testing .collides() method
    /**
     * A) Test collision with two entities that have same position
     */
    @Test
    public void testCollisionSamePosition() {
        // Creating entities and giving them a size (radius) and placement
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();

        entity1.setRadius(10);
        entity2.setRadius(10);

        entity1.setX(10);
        entity1.setY(10);
        entity2.setX(10);
        entity2.setY(10);

        boolean test = collisionDetector.collides(entity1, entity2);
        assertTrue(test); // Assert that they do in fact collide as they should
    }

    /**
     * B) Test collision when two entities do not overlap
     * */
    @Test
    public void testCollisionNoOverlap() {
        // Creating entities and giving them a size (radius) and placement
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();

        entity1.setRadius(10);
        entity2.setRadius(10);

        entity1.setX(40);
        entity1.setY(40);
        entity2.setX(10);
        entity2.setY(10);

        boolean test = collisionDetector.collides(entity1, entity2);
        assertFalse(test); // They should not collide
    }

    /**
     * C) Test collision when sizes are different but position the same
     * */
    @Test
    public void testCollisionDifferentSizes() {
        // Creating entities and giving them a size (radius) and placement
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();

        entity1.setRadius(10);
        entity2.setRadius(20);

        entity1.setX(10);
        entity1.setY(10);
        entity2.setX(10);
        entity2.setY(10);

        boolean test = collisionDetector.collides(entity1, entity2);
        assertTrue(test); // Assert that they do in fact collide as they should


    }

    // Testing .process() method
    /**
     * D) Test if two entities are identical (same ID)
     */
    @Test
    public void testCollisionIdenticalID() {
        // Creating entities and giving them a size (radius) and placement
        Entity entity1 = new Entity();
        Entity entity2 = entity1; // Duplicate

        entity1.setRadius(10);
        entity2.setRadius(10);

        entity1.setX(10);
        entity1.setY(10);
        entity2.setX(10);
        entity2.setY(10);

        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionDetector.process(gameData, world); // This should NOT remove them

        boolean exists1 = world.getEntity(entity1.getID()) != null;
        assertTrue(exists1); // Assert that they do in fact collide as they should

        boolean exists2 = world.getEntity(entity1.getID()) != null;
        assertTrue(exists2);
    }


    /**
     * E) Test if both entities are Bullets
     * */
    @Test
    public void testCollisionBothBullets() {
        // Bullets are created using BulletControlSystem's .createBullet() method, which cannot be mocked,
        // as it is not a dependence. The method behavior and settings are instead replicated.
        Entity bullet1 = new Entity();
        Entity bullet2 = new Entity();

        bullet1.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        bullet2.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);

        bullet1.setRadius(1);
        bullet2.setRadius(1);
        bullet1.setType("Bullet");
        bullet2.setType("Bullet");
        bullet1.setHealth(1);
        bullet2.setHealth(1);

        bullet1.setX(10);
        bullet2.setX(10);
        bullet1.setY(10);
        bullet2.setY(10);

        world.addEntity(bullet1);
        world.addEntity(bullet2);

        collisionDetector.process(gameData, world); // This should remove them

        boolean exists1 = world.getEntity(bullet1.getID()) != null;
        assertFalse(exists1); // Assert that they do in fact collide as they should

        boolean exists2 = world.getEntity(bullet2.getID()) != null;
        assertFalse(exists2);
    }

    /**
     * F) Test if both entities are Asteroids
     * */
    @Test
    public void testCollisionBothAsteroids() {
        // Asteroids are created using AsteroidPlugin's .createAsteroid() method, which cannot be mocked,
        // as it is not a dependence. The method behavior and settings are instead replicated.
        Entity asteroid1 = new Entity();
        Entity asteroid2 = new Entity();
        int size = 10;
        asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid2.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

        asteroid1.setRadius(size);
        asteroid2.setRadius(size);

        asteroid1.setType("Asteroid");
        asteroid2.setType("Asteroid");

        asteroid1.setHealth(1);
        asteroid2.setHealth(1);

        asteroid1.setX(10);
        asteroid2.setX(10);

        asteroid1.setY(10);
        asteroid2.setY(10);

        world.addEntity(asteroid1);
        world.addEntity(asteroid2);

        collisionDetector.process(gameData, world); // This should NOT remove them

        boolean exists1 = world.getEntity(asteroid1.getID()) != null;
        assertTrue(exists1); // Assert that they do in fact collide as they should

        boolean exists2 = world.getEntity(asteroid2.getID()) != null;
        assertTrue(exists2);
    }

    /**
     * G) Test if one is a bullet , but not the other entity (Asteroid)
     * */
    @Test
    public void testCollisionBulletAndNonBullet() {
        Entity asteroid1 = new Entity();
        Entity bullet2 = new Entity();
        int size = 10;
        asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        bullet2.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        asteroid1.setRadius(size);
        bullet2.setRadius(1);
        asteroid1.setType("Asteroid");
        bullet2.setType("Bullet");
        asteroid1.setHealth(1);
        bullet2.setHealth(1);
        asteroid1.setX(10);
        bullet2.setX(10);
        asteroid1.setY(10);
        bullet2.setY(10);
        world.addEntity(asteroid1);
        world.addEntity(bullet2);
        collisionDetector.process(gameData, world); // This should remove them
        boolean exists1 = world.getEntity(asteroid1.getID()) != null;
        assertFalse(exists1); // Assert that they do in fact collide as they should
        boolean exists2 = world.getEntity(bullet2.getID()) != null;
        assertFalse(exists2);
    }

    /**
     * H) Test if one entity is Player, and the other Enemy.<br>
     * I.E. not duplicate types, and neither is a bullet
     * */
    @Test
    public void testCollisionNonDuplicateAndNonBullet() {
        Entity asteroid1 = new Entity();
        Entity player2 = new Entity();
        int size = 10;
        asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        player2.setPolygonCoordinates(-5,-5,10,0,-5,5);

        asteroid1.setRadius(size);
        player2.setRadius(8);

        asteroid1.setType("Asteroid");
        player2.setType("Player");

        asteroid1.setHealth(1);
        player2.setHealth(5);

        asteroid1.setX(10);
        player2.setX(10);

        asteroid1.setY(10);
        player2.setY(10);

        world.addEntity(asteroid1);
        world.addEntity(player2);

        collisionDetector.process(gameData, world); // This should remove them

        boolean exists1 = world.getEntity(asteroid1.getID()) != null;
        assertFalse(exists1); // Assert that they do in fact collide as they should

        boolean exists2 = world.getEntity(player2.getID()) != null;
        assertFalse(exists2);
    }

    /**
     * I) Test if undefined Entities collide. <br>
     * */
    @Test
    public void testCollisionUndefined() {
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        int size = 10;
        entity1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        entity2.setPolygonCoordinates(-5,-5,10,0,-5,5);

        entity1.setRadius(size);
        entity2.setRadius(8);

        entity1.setX(10);
        entity2.setX(10);

        entity1.setY(10);
        entity2.setY(10);

        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionDetector.process(gameData, world); // This should remove them

        boolean exists1 = world.getEntity(entity1.getID()) != null;
        assertFalse(exists1); // Assert that they do in fact collide as they should

        boolean exists2 = world.getEntity(entity2.getID()) != null;
        assertFalse(exists2);
    }
}