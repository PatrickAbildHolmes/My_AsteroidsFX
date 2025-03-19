package dk.sdu.cbse;

import dk.sdu.cbse.Entity;
import dk.sdu.cbse.GameData;

public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
