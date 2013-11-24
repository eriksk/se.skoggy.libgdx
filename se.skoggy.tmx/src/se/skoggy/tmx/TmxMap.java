package se.skoggy.tmx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TmxMap {

	public String name;
	public int width, height, tilewidth, tileheight;
	public int version;
	public List<TmxTileSet> tilesets;
	public List<TmxLayer> layers;
	public HashMap<String, String> properties;
	
	public TmxMap(){
		properties = new HashMap<String, String>();
		layers = new ArrayList<TmxLayer>();
	}
	
	public int widthInPixels(){
		return width * tilewidth;
	}

	public int heightInPixels(){
		return height * tileheight;
	}
}
