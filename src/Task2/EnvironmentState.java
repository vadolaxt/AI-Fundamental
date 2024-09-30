package Task2;

import Task2.Environment.LocationState;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentState {
    private Map<String, LocationState> state = new HashMap<String, LocationState>();
    private String agentLocation = null;//

    public EnvironmentState(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
        this.state.put(Environment.LOCATION_A, locAState);
        this.state.put(Environment.LOCATION_B, locBState);
        this.state.put(Environment.LOCATION_C, locCState);
        this.state.put(Environment.LOCATION_D, locDState);
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
        boolean cleanC = getLocationState(Environment.LOCATION_C).equals(LocationState.CLEAN);
        boolean cleanD = getLocationState(Environment.LOCATION_D).equals(LocationState.CLEAN);
        return cleanA && cleanB && cleanC && cleanD;
    }

    public void display() {
        System.out.println("Environment state: \n\t" + this.state);
    }
}