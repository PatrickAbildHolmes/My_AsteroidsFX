package dk.sdu.cbse.player;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.GameKeys;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IEntityProcessingService;
import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);                
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);                
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {                
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(player, gameData));}
                );
            }
        if (player.getX() < 0) {
            player.setX(1);
        }
        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(gameData.getDisplayWidth()-1);
        }
        if (player.getY() < 0) {
            player.setY(1);
        }
        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(gameData.getDisplayHeight()-1);
        }
        }
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
