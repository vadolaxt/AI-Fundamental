package Task3;

import java.util.*;

public class Environment {
    public static final int m = 3;
    public static final int n = 3;
    public static final double WALL_RATE = 0.2;
    public static final List<String> matrix = new ArrayList<String>(); //matrix cells = m*n
    public static final List<Integer> leftLimit = new ArrayList<>();
    public static final List<Integer> rightLimit = new ArrayList<>();
    public static final List<Integer> upLimit = new ArrayList<>();
    public static final List<Integer> downLimit = new ArrayList<>();

    public enum LocationState {
        CLEAN, DIRTY, WALL
    }

    private EnvironmentState model;
    private Agent agent = null;// single, for multi-agent using List<Agent>

    public Environment(List<LocationState> state) {
        model = new EnvironmentState(state);
        addWalll();
    }

    // add an agent into the environment
    public void addAgent(Agent agent, String location) {
        // TODO
        model.setAgentLocation(location);
        this.agent = agent;
    }

    /* name matrix cell as number, for ex
    0 1 2
    3 4 5
    6 7 8
     */
    public static void addValue() {
        for (int i = 0; i < m * n; i++) {
            matrix.add(String.valueOf(i));
        }
    }

    // add random wall to matrix
    public void addWalll() {
        int wallNum = (int) (WALL_RATE * m * n); // numbers of wall base on WALL_RATE
        while (wallNum != 0) {
            int ran = (int) (Math.random() * (m * n)); //random position
            if (model.getLocationState(matrix.get(ran)).equals(LocationState.CLEAN)) { // check whether cell' state is WALL or not
                model.setLocationState(matrix.get(ran), LocationState.WALL);
                wallNum--;
            }
        }
    }

    // create limit border for matrix
    /*
    For ex:
    0 1 2 3
    4 5 6 7
    8 9 10 11

    Left limit: 0 4 8
    Right limit: 3 7 11
    Up limit: 0 1 2 3
    Down limit: 8 9 10 11
     */
    public static void createLimit() { //add value of cell which match the kind of limit border (left or right or up or down limit)
        for (int i = 0; i < (m - 1) * n + 1; i++) {
            if (i % n == 0) {
                leftLimit.add(i);
            }
        }
        int temp = n - 1;
        for (int i = n - 1; i < m * n; i++) {
            if (i == temp) {
                rightLimit.add(i);
                temp += m;
            }
        }
        for (int i = 0; i < n; i++) {
            upLimit.add(i);
        }
        for (int i = (m - 1) * n; i < m * n; i++) {
            downLimit.add(i);
        }
    }

    public EnvironmentState getCurrentState() {
        return this.model;
    }

    // Update environment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        // TODO
        createLimit();
        String aLoc = model.getAgentLocation();
        int loc = Integer.parseInt(aLoc);
        if (action.equals(Action.SUCK)) {
            model.setLocationState(aLoc, LocationState.CLEAN);

        } else if (action.equals(NoOpAction.NO_OP)) {
            //do nothing

        } else if (action.equals(Action.LEFT)) {
            model.setAgentLocation(String.valueOf(loc - 1));
        } else if (action.equals(Action.RIGHT)) {
            model.setAgentLocation(String.valueOf(loc + 1));
        } else if (action.equals(Action.UP)) {
            model.setAgentLocation(String.valueOf(loc - n));
        } else if (action.equals(Action.DOWN)) {
            model.setAgentLocation(String.valueOf(loc + n));
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
        model.display(); //env at the beginning
        while (!model.isClean()) {
            Percept p = getPerceptSeenBy(); // percept at agent location
            Action act = agent.execute(p);
             /*
            Agent' action is printed then the state of the env after agent' action is printed
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

