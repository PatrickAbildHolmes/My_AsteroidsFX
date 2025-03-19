package dk.sdu.cbse;

import dk.sdu.cbse.GameData;
import dk.sdu.cbse.World;

public interface IGamePluginService {

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}

