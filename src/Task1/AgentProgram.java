package Task1;

public class AgentProgram {
    //return an appropriate action based on the given percept
    public Action execute(Percept p) {// location, status
        // TODO
        if (p.getLocationState() == Environment.LocationState.DIRTY) {
            return Action.SUCK;
        } else if (p.getAgentLocation().equals(Environment.LOCATION_A)) {
            return Action.RIGHT;
        } else {
            return Action.LEFT;
        }
    }
}