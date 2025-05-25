package dk.sdu.cbse.conflict;

import dk.sdu.cbse.common.ISplitConflict;

public class ConflictingProcessor implements ISplitConflict {
    @Override
    public String testSplitConflict() {
        return("Conflicting as ----EnemyShip----");
    }
}
