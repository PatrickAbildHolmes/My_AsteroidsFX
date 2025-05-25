package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ISplitConflict;

public class ConflictingProcessor implements ISplitConflict {
    @Override
    public String testSplitConflict() {
        return("Conflicting as [[[[Player]]]]");
    }
}
