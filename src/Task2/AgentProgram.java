package Task2;

public class AgentProgram {
    //return an appropriate action based on the given percept
    public Action execute(Percept p) { // location, status
        // TODO
        /*
        Random action if env is CLEAN
        0: Left
        1: Right
        2: Up
        3: Down
         */
        int ranNum = (int) (Math.random() * 4);
        if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
            return Action.SUCK;

        } else if (p.getAgentLocation().equals(Environment.LOCATION_A) && ranNum == 1) {
            return Action.RIGHT;
        } else if (p.getAgentLocation().equals(Environment.LOCATION_A) && ranNum == 3) {
            return Action.DOWN;

        } else if (p.getAgentLocation().equals(Environment.LOCATION_B) && ranNum == 0) {
            return Action.LEFT;
        } else if (p.getAgentLocation().equals(Environment.LOCATION_B) && ranNum == 3) {
            return Action.DOWN;

        } else if (p.getAgentLocation().equals(Environment.LOCATION_C) && ranNum == 1) {
            return Action.RIGHT;
        } else if (p.getAgentLocation().equals(Environment.LOCATION_C) && ranNum == 2) {
            return Action.UP;

        } else if (p.getAgentLocation().equals(Environment.LOCATION_D) && ranNum == 0) {
            return Action.LEFT;
        } else if (p.getAgentLocation().equals(Environment.LOCATION_D) && ranNum == 2) {
            return Action.UP;
        } else {
            return NoOpAction.NO_OP;
        }
    }
}

