import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.ISplitConflict;

module Player {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.player.PlayerControlSystem;
    provides ISplitConflict with dk.sdu.cbse.player.ConflictingProcessor;
}