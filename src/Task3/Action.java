package Task3;

public abstract class Action {
    public static final Action LEFT = new DynamicAction("LEFT");
    public static final Action RIGHT = new DynamicAction("RIGHT");
    public static final Action UP = new DynamicAction("UP");
    public static final Action DOWN = new DynamicAction("DOWN");
    public static final Action SUCK = new DynamicAction("SUCK");

    public abstract boolean isNoOp();
}