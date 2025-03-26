module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    opens dk.sdu.cbse.core to javafx.graphics;
    uses dk.sdu.cbse.common.IGamePluginService;
    uses dk.sdu.cbse.common.IEntityProcessingService;
    uses dk.sdu.cbse.common.IPostEntityProcessingService;
}