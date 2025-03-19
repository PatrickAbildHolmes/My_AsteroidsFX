module Core {
    uses dk.sdu.cbse.common.IGamePluginService;
    uses dk.sdu.cbse.common.IEntityProcessingService;
    uses dk.sdu.cbse.common.IPostEntityProcessingService;
    requires javafx.graphics;
    requires Common;
    requires CommonBullet;
    opens dk.sdu.cbse.core to javafx.graphics;
}