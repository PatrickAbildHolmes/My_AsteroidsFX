import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.IGamePluginService;

module EnemyShip {
    requires Common;
    requires CommonBullet;
    requires CommonEnemyShips;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.enemyship.EnemyShipPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemyship.EnemyShipProcessor;
}