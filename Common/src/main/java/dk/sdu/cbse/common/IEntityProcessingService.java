package dk.sdu.cbse.common;

public interface IEntityProcessingService {
    /**
     * This method is used to implement actions or changes to entities,
     * e.g. splitting an Asteroid into smaller asteroids or binding keyboard buttons to actions.
     * @param gameData Display size (X, Y) of the game world, and keyboard presses.
     * @param world The game World object that the Entity is added to.
     * */
    void process(GameData gameData, World world);
}

