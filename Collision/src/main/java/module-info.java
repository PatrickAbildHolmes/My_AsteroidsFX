import dk.sdu.cbse.common.IPostEntityProcessingService;

module Collision {
    requires Common;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;
}