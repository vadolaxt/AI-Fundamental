package Task3;

import java.util.ArrayList;
import java.util.List;

public class TestSimpleReflexAgent {
    public static void main(String[] args) {
        List<Environment.LocationState> state = new ArrayList<>();
        state.add(Environment.LocationState.CLEAN);
        state.add(Environment.LocationState.DIRTY);
        state.add(Environment.LocationState.CLEAN);
        state.add(Environment.LocationState.CLEAN);
        state.add(Environment.LocationState.CLEAN);
        state.add(Environment.LocationState.CLEAN);
        state.add(Environment.LocationState.DIRTY);
        state.add(Environment.LocationState.CLEAN);
        state.add(Environment.LocationState.CLEAN);

        Environment env = new Environment(state);
        Agent agent = new Agent(new AgentProgram());
        env.addAgent(agent, "0");
        env.run();
//        Environment.createLimit();
//        System.out.println(Environment.leftLimit.toString());
//        System.out.println(Environment.rightLimit.toString());
//        System.out.println(Environment.upLimit.toString());
//        System.out.println(Environment.downLimit.toString());
    }
}
