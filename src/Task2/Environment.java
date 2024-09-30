package Task2;

public class Environment {
    public static final String LOCATION_A = "A";
    public static final String LOCATION_B = "B";
    public static final String LOCATION_C = "C";
    public static final String LOCATION_D = "D";


    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState model;
    private Agent agent = null;// single, for multi-agent using List<Agent>

    public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
        model = new EnvironmentState(locAState, locBState, locCState, locDState);
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
        String aLoc = model.getAgentLocation(); //agent location
        if (action.equals(Action.SUCK)) {
            model.setLocationState(aLoc, LocationState.CLEAN);

        } else if (action.equals(NoOpAction.NO_OP)) {
            //do nothing

        } else if (aLoc.equals(LOCATION_A) && action.equals(Action.RIGHT)) {
            model.setAgentLocation(LOCATION_B);
        } else if (aLoc.equals(LOCATION_A) && action.equals(Action.DOWN)) {
            model.setAgentLocation(LOCATION_C);

        } else if (aLoc.equals(LOCATION_B) && action.equals(Action.LEFT)) {
            model.setAgentLocation(LOCATION_A);
        } else if (aLoc.equals(LOCATION_B) && action.equals(Action.DOWN)) {
            model.setAgentLocation(LOCATION_D);

        } else if (aLoc.equals(LOCATION_C) && action.equals(Action.RIGHT)) {
            model.setAgentLocation(LOCATION_D);
        } else if (aLoc.equals(LOCATION_C) && action.equals(Action.UP)) {
            model.setAgentLocation(LOCATION_A);

        } else if (aLoc.equals(LOCATION_D) && action.equals(Action.LEFT)) {
            model.setAgentLocation(LOCATION_C);
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
        int point = 0; // performance measure
        model.display(); // moi truong luc chua thuc hien Action
        while (!model.isClean()) {
            Percept p = getPerceptSeenBy(); // percept at agent location
            Action act = agent.execute(p);
            /*
            Agent' action is printed then printed the state of the env after agent' action
             */
            if (act.equals(Action.SUCK)) {
                point += 500;
                executeAction(act);
                System.out.println("Agent Loc.: " + p.getAgentLocation() + "\t" + "Action: " + act.toString());
                model.display();
            } else if (act.equals(NoOpAction.NO_OP)) {
                point -= 100;
                executeAction(act);
                System.out.println("Agent Loc.: " + p.getAgentLocation() + "\t" + "Action: " + act.toString());
                model.display();
            } else {
                point -= 10;
                executeAction(act);
                System.out.println("Agent Loc.: " + p.getAgentLocation() + "\t" + "Action: " + act.toString());
                model.display();
            }
        }
        System.out.println("Agent performance point: " + point); // agent' performance measure
    }
}
