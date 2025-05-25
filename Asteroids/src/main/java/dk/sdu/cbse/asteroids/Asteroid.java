package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.IEntityRemoval;
import dk.sdu.cbse.common.World;

public class Asteroid extends Entity implements IEntityRemoval {
    private final AsteroidSplitterImpl asteroidSplitter;

    public Asteroid() {
        this.asteroidSplitter = new AsteroidSplitterImpl();
    }

    @Override
    public void onRemoval(Entity entity, World world) {
        asteroidSplitter.createSplitAsteroid(entity, world);
    }
}
