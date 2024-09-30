package Task1;

import java.util.HashMap;
import java.util.Map;

import Task1.Environment.LocationState;

public class EnvironmentState {
    private Map<String, Environment.LocationState> state = new HashMap<String, Environment.LocationState>();
    private String agentLocation = null;//

    public EnvironmentState(Environment.LocationState locAState, Environment.LocationState locBState) {
        this.state.put(Environment.LOCATION_A, locAState);
        this.state.put(Environment.LOCATION_B, locBState);
    }

    public void setAgentLocation(String location) {
        this.agentLocation = location;
    }

    public String getAgentLocation() {
        return this.agentLocation;
    }

    public LocationState getLocationState(String location) {
        return this.state.get(location);
    }

    public void setLocationState(String location, LocationState locationState) {
        this.state.put(location, locationState);
    }

    // check whether all locations are clean?
    public boolean isClean() {
        // TODO
        boolean cleanA = getLocationState(Environment.LOCATION_A).equals(LocationState.CLEAN);
        boolean cleanB = getLocationState(Environment.LOCATION_B).equals(LocationState.CLEAN);
        return cleanA && cleanB;
    }

    public void display() {
        System.out.println("Environment state: \n\t" + this.state);
    }
}