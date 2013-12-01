package se.skoggy.utils;

public class Helpers {

	public static float lerp(float x, float y, float w){
		return x + (y - x) * w;
	}

}
