package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import org.junit.jupiter.api.Test;

/**
 * 9 Tests.<br>
 * 3 tests that test whether the .collides() method works correctly (I.E. collision is detected correctly).<br>
 * 5 tests that should assert True by correctly simulating how a collision between Entities of the respective types should behave.<br>
 * And 1 test that should assert False by correctly identifying a lack of collision.
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
    public void testCollisionSamePosition() {}

    /**
     * B) Test collision when two entities do not overlap
     * */
    @Test
    public void testCollisionNoOverlap() {}

    /**
     * C) Test collision when sizes are different but position the same
     * */
    @Test
    public void testCollisionDifferentSizes() {}


    // Testing .process() method
    /**
     * D) Test if two entities are identical (same ID)
     */
    @Test
    public void testCollisionIdenticalID() {}


    /**
     * E) Test if both entities are Bullets
     * */
    @Test
    public void testCollisionBothBullets() {}

    /**
     * F) Test if both entities are Asteroids
     * */
    @Test
    public void testCollisionBothAsteroids() {}

    /**
     * G) Test if one, but not the other entity, is a bullet (Asteroid)
     * */
    @Test
    public void testCollisionBulletAndNonBullet() {}

    /**
     * H) Test if one entity is Player, and the other Enemy.<br>
     * I.E. not duplicate types, and neither is a bullet
     * */
    @Test
    public void testCollisionNonDuplicateAndNonBullet() {}


    /**
     * I)  Test if entities are not withing collision range
     * */
    @Test
    public void testCollisionNotInRange() {}
}
