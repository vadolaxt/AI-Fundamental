package Task1;

public class Environment {
    public static final String LOCATION_A = "A";
    public static final String LOCATION_B = "B";

    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState model;
    private Agent agent = null;// single, for multi-agent using List<Agent>

    public Environment(LocationState locAState, LocationState locBState) {
        model = new EnvironmentState(locAState, locBState);
    }

    // add an agent into the environment
    public void addAgent(Agent agent, String location) {
        // TODO
        model.setAgentLocation(location);
        this.agent = agent;
    }

    public EnvironmentState getCurrentState() {
        return this.model;
    }

    // Update environment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        // TODO
        String aLocation = model.getAgentLocation();
        if (action.equals(Action.SUCK)) {
            model.setLocationState(aLocation, LocationState.CLEAN);
        } else if (action.equals(Action.LEFT)) {
            model.setAgentLocation(LOCATION_A);
        } else {
            model.setAgentLocation(LOCATION_B);
        }
        return getCurrentState();
    }

    // get the percept<AgentLocation, LocationState> at the current location where agent is in.
    public Percept getPerceptSeenBy() {
        // TODO
        String location = model.getAgentLocation();
        LocationState state = model.getLocationState(location);
        return new Percept(location, state);
    }

    public void run() {
        // TODO
        model.display(); // moi truong luc chua thuc hien Action
        while (!model.isClean()) {
            Percept aPercept = getPerceptSeenBy(); //percept tai vtri agent
            Action act = agent.execute(aPercept);
            if (aPercept.getLocationState().equals(LocationState.CLEAN)) {
                executeAction(act);
                System.out.println("Agent Loc.: " + aPercept.getAgentLocation() + "\t" + "Action: " + act.toString());
                model.display();
            } else {
                executeAction(act);
                System.out.println("Agent Loc.: " + aPercept.getAgentLocation() + "\t" + "Action: " + act.toString());
                model.display();
            }
        }
    }
}