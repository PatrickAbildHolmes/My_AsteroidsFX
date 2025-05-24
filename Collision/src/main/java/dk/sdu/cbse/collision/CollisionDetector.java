package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    // If either entity is a bullet, remove 1 hp from both entities.
                    // Else remove both entities
                    if (entity1.getType().equals("Bullet")||entity2.getType().equals("Bullet")) {
                        if (entity1.getHealth()>1){
                            entity1.setHealth(entity1.getHealth()-1);
                        } else {world.removeEntity(entity1);}

                        if (entity1.getHealth()>1){
                            entity1.setHealth(entity1.getHealth()-1);
                        } else {world.removeEntity(entity1);}
                    } else {
                        world.removeEntity(entity1);
                        world.removeEntity(entity2);
                    }
                    System.out.println("Collision between "+entity1.getType() + " and " + entity2.getType());
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
