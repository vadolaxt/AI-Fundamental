package Task3;

public class AgentProgram {
    //return an appropriate action based on the given percept
    public Action execute(Percept p) {// location, status
        // TODO
        /*
        Random action if env is CLEAN
        0: Left
        1: Right
        2: Up
        3: Down
         */
        int ranNum = (int) (Math.random() * 4); // random action
        String aLoc = p.getAgentLocation(); // agent location
        Environment.createLimit();

        if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
            return Action.SUCK;

        } else { // env state is CLEAN OR WALL

            /*
            check whether agent can move or not base on agent' action and agent location (matrix limit border)
             */
            if (ranNum == 0 && !Environment.leftLimit.contains(Integer.parseInt(aLoc))) {
                return Action.LEFT;
            } else if (ranNum == 1 && !Environment.rightLimit.contains(Integer.parseInt(aLoc))) {
                return Action.RIGHT;
            } else if (ranNum == 2 && !Environment.upLimit.contains(Integer.parseInt(aLoc))) {
                return Action.UP;
            } else if (ranNum == 3 && !Environment.downLimit.contains(Integer.parseInt(aLoc))) {
                return Action.DOWN;
            } else {
                return NoOpAction.NO_OP;
            }
        }
    }
}
