package dk.sdu.cbse.common;

public interface IPostEntityProcessingService {

    /**
     * This method is used to detect collision when two non-identical objects (e.g. Asteroid and Player) collides,
     * by calculating if the distance between their coordinates is less than their combined radius
     * (I.E., there is no empty space between Asteroid and Player).
     * @param gameData Display size (X, Y) of the game world.
     * @param world The game World object that the Entity is added to.
     * */
    void process(GameData gameData, World world);
}
