package dk.sdu.cbse.enemyship;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.enemyships.EnemyShip;
import dk.sdu.cbse.common.IGamePluginService;

import java.util.Random;

public class EnemyShipPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity enemyShip = createEnemyShip(gameData);
        world.addEntity(enemyShip);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity enemyShip : world.getEntities(EnemyShip.class)) {
            world.removeEntity(enemyShip);
        }
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new EnemyShip();
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5; // new size.
        enemyShip.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        enemyShip.setX(0);
        enemyShip.setY(0);
        enemyShip.setRadius(size);
        enemyShip.setRotation(rnd.nextInt(90));
        return enemyShip;
    }
}
