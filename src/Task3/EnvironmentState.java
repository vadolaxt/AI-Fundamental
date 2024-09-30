package Task3;

import Task3.Environment.LocationState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvironmentState {
    private Map<String, LocationState> state = new HashMap<String, LocationState>();
    private String agentLocation = null;//

    public EnvironmentState(List<LocationState> stat) {
        Environment.addValue(); // use this method cause matrix at the beginning is null
        for (int i = 0; i < (Environment.m * Environment.n); i++) {
            this.state.put(Environment.matrix.get(i), stat.get(i));
        }
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
        int count = 0; // count the number of cells which is clean, number of CLEAN cells= m*n - WALL cells
        for (int i = 0; i < Environment.matrix.size(); i++) {
            if (getLocationState(Environment.matrix.get(i)).equals(LocationState.CLEAN)) {
                count++;
            }
        }
        return count == (Environment.m * Environment.n) - (int) (Environment.m * Environment.n * Environment.WALL_RATE) ? true : false;
    }

    public void display() {
        System.out.println("Environment state: \n\t" + this.state);
    }
}