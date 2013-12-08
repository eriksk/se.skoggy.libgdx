package se.skoggy.input;

public abstract class InputController {

	public abstract void clearStates();

	public abstract void register(int code);

	/**
	 * Sets new and old states
	 */
	public abstract void update();

	public abstract InputState getOldState();
	public abstract InputState getCurrentState();
}
