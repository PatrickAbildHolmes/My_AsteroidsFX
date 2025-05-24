import dk.sdu.cbse.common.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroids;
    uses dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;
}