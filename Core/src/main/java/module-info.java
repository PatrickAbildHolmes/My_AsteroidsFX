module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.web;
    exports dk.sdu.cbse.core;
    opens dk.sdu.cbse.core to javafx.graphics, spring.core;
    uses dk.sdu.cbse.common.IGamePluginService; // Asteroids, Bullet, EnemyShip, Player
    uses dk.sdu.cbse.common.IEntityProcessingService; // Asteroids, Bullet, EnemyShip, Player
    uses dk.sdu.cbse.common.IPostEntityProcessingService; // Collision
}