package dk.sdu.cbse.common.asteroids;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.World;

public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}
