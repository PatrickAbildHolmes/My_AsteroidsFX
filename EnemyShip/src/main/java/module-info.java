import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.ISplitConflict;

module EnemyShip {
    requires Common;
    requires CommonBullet;
    requires CommonEnemyShips;
    exports dk.sdu.cbse.conflict;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.enemyship.EnemyShipPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemyship.EnemyShipProcessor;
    provides ISplitConflict with dk.sdu.cbse.conflict.ConflictingProcessor;
}