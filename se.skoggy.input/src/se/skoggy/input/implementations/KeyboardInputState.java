package se.skoggy.input.implementations;

import se.skoggy.input.InputState;

public class KeyboardInputState extends InputState{

	protected HashMap<int, boolean> codes;

	public KeyboardInputState() {
		codes = new boolean[0];
	}

	@Override
	public boolean getByCode(int code) {
		// TODO Auto-generated method stub
		return false;
	}

}
