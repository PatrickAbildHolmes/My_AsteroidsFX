package dk.sdu.cbse.common;

public interface IGamePluginService {

    /**
     * This method is used to add entities (Player, Asteroids, Bullet, EnemyShip et.c) to the game world,
     * when certain conditions are met (e.g. set interval re/spawning).
     * @param gameData Display size (X, Y) of the game world. E.g. used to determine starting position.
     * @param world The game World object that the Entity is added to.
     * */
    void start(GameData gameData, World world);

    /**
     * This method is used to remove entities (Player, Asteroids, Bullet, EnemyShip et.c) from the game World,
     * when certain conditions are met (e.g. collision).
     * @param gameData Display size (X, Y) of the game world.
     * @param world The game World object that the Entity is added to.
     * */
    void stop(GameData gameData, World world);
}

