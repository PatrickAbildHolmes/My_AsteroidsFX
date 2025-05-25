module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    opens dk.sdu.cbse.core to javafx.graphics;
    uses dk.sdu.cbse.common.IGamePluginService; // Asteroids, Bullet, EnemyShip, Player
    uses dk.sdu.cbse.common.IEntityProcessingService; // Asteroids, Bullet, EnemyShip, Player
    uses dk.sdu.cbse.common.IPostEntityProcessingService; // Collision
}