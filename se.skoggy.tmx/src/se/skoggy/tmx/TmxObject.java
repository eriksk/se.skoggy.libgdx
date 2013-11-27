package se.skoggy.tmx;

import java.util.HashMap;

public class TmxObject {

	public String name;
	public int x, y, width, height;
	public String type;
	public boolean visible;
	public HashMap<String, String> properties;

	public TmxObject(){
		properties = new HashMap<>();
	}

	public float getCenterX(){
		return x + width * 0.5f;
	}

	public float getCenterY(){
		return y + height * 0.5f;
	}
}
