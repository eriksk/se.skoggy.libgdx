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
	
	public void setToTrigNextTime(){
		current = interval;
	}
}
