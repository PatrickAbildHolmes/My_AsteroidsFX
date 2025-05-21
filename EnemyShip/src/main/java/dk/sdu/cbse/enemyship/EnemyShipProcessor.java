package dk.sdu.cbse.enemyship;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.enemyships.EnemyShip;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyShipProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemyShip : world.getEntities(EnemyShip.class)) {
            Random random = new Random();
            // enemy ship turns randomly
            enemyShip.setRotation(enemyShip.getRotation()+random.nextInt(3));

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
            if (enemyShip.getX() < 0) {
                enemyShip.setX(enemyShip.getX() + gameData.getDisplayWidth());
            }

            if (enemyShip.getX() > gameData.getDisplayWidth()) {
                enemyShip.setX(enemyShip.getX() % gameData.getDisplayWidth());
            }

            if (enemyShip.getY() < 0) {
                enemyShip.setY(enemyShip.getY() + gameData.getDisplayHeight());
            }

            if (enemyShip.getY() > gameData.getDisplayHeight()) {
                enemyShip.setY(enemyShip.getY() % gameData.getDisplayHeight());
            }
        }
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
