package Task2;

public class NoOpAction extends Action {
	public static final NoOpAction NO_OP = new NoOpAction();

	public boolean isNoOp() {
		return true;
	}

	@Override
	public String toString() {
		return "NO ACTION";
	}
}