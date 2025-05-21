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
        System.out.println("Spawning Enemy ship" + enemyShip.getID());
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity enemyShip : world.getEntities(EnemyShip.class)) {
            world.removeEntity(enemyShip);
        }
    }

    private Entity createEnemyShip(GameData gameData) {
        System.out.println("Creating Enemy Ship");
        Entity enemyShip = new EnemyShip();
        Random rnd = new Random();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setRadius(8);
        enemyShip.setRotation(rnd.nextInt(90));

        // Random spawn location along X-axis
        int randomX = rnd.nextInt(gameData.getDisplayWidth());
        enemyShip.setX(randomX);
        enemyShip.setY(0);
        return enemyShip;
    }
}
