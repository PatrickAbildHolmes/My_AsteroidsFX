package dk.sdu.cbse.enemyship;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.enemyships.EnemyShip;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IEntityProcessingService;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyShipProcessor implements IEntityProcessingService {
    EnemyShipPlugin newEnemies;
    int numOfEnemiesPresent;
    public EnemyShipProcessor() {
        this.newEnemies = new EnemyShipPlugin(); // makes more enemy ships
        this.numOfEnemiesPresent = 0;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemyShip : world.getEntities(EnemyShip.class)) {
            this.numOfEnemiesPresent++;
            Random random = new Random();
            // enemy ship turns randomly
            enemyShip.setRotation(enemyShip.getRotation()+random.nextInt(2));

            // ------ Move towards player, but slower ------
            double speedMultiplier = 0.7;
            double changeX = Math.cos(Math.toRadians(enemyShip.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemyShip.getRotation()));
            enemyShip.setX(enemyShip.getX() + changeX * speedMultiplier);
            enemyShip.setY(enemyShip.getY() + changeY * speedMultiplier);

            // ------ and sometimes shoot for a few frames ------
            if (random.nextInt(100)<5) { // 5% per frame it shoots
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {
                            world.addEntity(spi.createBullet(enemyShip, gameData));
                        }
                );
            }
            // Enemy ships now get destroyed when exiting the window
            float screenWidth = gameData.getDisplayWidth();
            float screenHeight = gameData.getDisplayHeight();
            if((enemyShip.getX() < 0) || (enemyShip.getX() > screenWidth) || (enemyShip.getY() < 0) || (enemyShip.getY() > screenHeight)) {
                world.removeEntity(enemyShip);
                try {
                    gameData.increaseAsteroidsKilled(); // added difficulty
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // Block for respawning enemy ships if less than 1 is present
        if (numOfEnemiesPresent < 1) {
            try {
                gameData.increaseEnemiesKilled(); // count up on respawn (easier to do here than in Collision)
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newEnemies.start(gameData, world);
        }
        // Reset numOfEnemiesPresent counting
        numOfEnemiesPresent = 0;
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
