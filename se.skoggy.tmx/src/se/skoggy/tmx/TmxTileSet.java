package se.skoggy.tmx;

import java.util.HashMap;

public class TmxTileSet {

	public int firstgid;
	public String name;
	public String image;
	public int tilewidth, tileheight, imagewidth, imageheight;
	public int margin;
	public int spacing;
	public HashMap<String, String> properties;
	
	public TmxTileSet() {
		properties = new HashMap<String, String>();
	}
}
