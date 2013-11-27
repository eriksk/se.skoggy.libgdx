package se.skoggy.utils;

public class TimerTrig {

	float interval;
	float current;

	public TimerTrig(float interval) {
		this.interval = interval;
	}

	public boolean isTrigged(float dt){
		current += dt;
		if(current >= interval){
			current = 0f;
			return true;
		}
		return false;
	}

	/**
	 * Updates without trigging
	 */
	public void update(float dt){
		current += dt;
		if(current > interval){
			current = interval;
		}
	}

	public void setToTrigNextTime(){
		current = interval;
	}
}
