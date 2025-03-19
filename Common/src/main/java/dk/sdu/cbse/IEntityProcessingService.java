package dk.sdu.cbse;

import dk.sdu.cbse.GameData;
import dk.sdu.cbse.World;

public interface IEntityProcessingService {
    void process(GameData gameData, World world);
}

