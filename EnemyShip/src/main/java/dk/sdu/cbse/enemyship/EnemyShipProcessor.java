package dk.sdu.cbse.enemyship;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.enemyships.EnemyShip;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyShipProcessor implements IEntityProcessingService {
    // Counters to determine if enemy ship can shoot
    private final int clipSize = 8;
    private int bulletMagazine = clipSize; // Can shoot for 8 frames in a row
    private int reloading = 0;

    // Enemy ships constantly targets Player, and shoots streams of bullets
    @Override
    public void process(GameData gameData, World world) {
        // Every frame, load the Player position
        Entity player = world.getEntity(gameData.getPlayerID());
        for (Entity enemyShip : world.getEntities(EnemyShip.class)) {
            // ------ Every frame, calculate rotation angle to face player ------

            // Method for pivoting precisely to a location (offset from "3 o'clock") from a fixed point (enemyShip location)
            // Not nearly as cool
            // enemyShip.setRotation(Math.atan2(player.getY(),player.getX())); //Math.atan2 returns angle from "3 o'clock" towards

            // Method for turning towards player instead
            double dx = player.getX()-enemyShip.getX();
            double dy = player.getY()-enemyShip.getY();
            double targetAngle = Math.atan2(dy, dx);
            double angleDifference = normalizeAngle(targetAngle - enemyShip.getRotation());

            double enemyTurnSpeed = Math.toRadians(5); // Similar to how Player turns with 5 degrees per frame

            // If angleDifference is greater than the turn speed degrees, we instead turn by the maximum speed we can
            if (angleDifference > enemyTurnSpeed) {
                angleDifference = enemyTurnSpeed;
            } else if (angleDifference < -enemyTurnSpeed) {
                angleDifference = -enemyTurnSpeed;
            }
            // We apply the rotation change to current rotation, normalize it (for safety), and then assign it as the new direction
            enemyShip.setRotation(normalizeAngle(enemyShip.getRotation() + angleDifference));

            // ------ Move towards player, but slower ------
            double speedMultiplier = 0.5;
            double changeX = Math.cos(Math.toRadians(player.getRotation()));
            double changeY = Math.sin(Math.toRadians(player.getRotation()));
            player.setX(player.getX() + changeX * speedMultiplier);
            player.setY(player.getY() + changeY * speedMultiplier);

            // ------ and sometimes shoot for a few frames ------
            if (bulletMagazine > 0) {
                bulletMagazine--;
                // Shoot
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(enemyShip, gameData));}
                );
            } else {
                reloading++; // Starts at 0. If no bullets left in magazine, reload for a frame,
                if (reloading % 10 == 0) {      // and when has been reloading for 10 seconds
                    reloading = 0;              // reset reloading,
                    bulletMagazine = clipSize;  // and refresh magazine
                }
            }
        }
    }
    double normalizeAngle(double angle) {
        while (angle > Math.PI) {angle -= 2 * Math.PI;}
        while (angle < -Math.PI) {angle += 2 * Math.PI;}
        return angle;
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
