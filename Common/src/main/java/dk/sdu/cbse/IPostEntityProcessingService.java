package dk.sdu.cbse;

import dk.sdu.cbse.GameData;
import dk.sdu.cbse.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
