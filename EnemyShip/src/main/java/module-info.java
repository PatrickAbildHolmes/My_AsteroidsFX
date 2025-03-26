import dk.sdu.cbse.common.IGamePluginService;

module EnemyShip {
    requires CommonEnemyShips;
    requires Common;
    provides IGamePluginService with dk.sdu.cbse.enemyship.EnemyShipPlugin;
}