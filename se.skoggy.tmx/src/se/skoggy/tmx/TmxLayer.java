package se.skoggy.tmx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TmxLayer {

	public String name, type;
	public int[] data;
	public List<TmxObject> objects;
	public int width, height;
	public float opacity;
	public boolean visible;
	public int x, y;
	public HashMap<String, String> properties;
	
	public TmxLayer(){
		data = new int[0];
		objects = new ArrayList<TmxObject>();
		properties = new HashMap<String, String>();
	}
	
	public int getCell(int col, int row){
		if(col < 0)
			return 0;
		if(row < 0)
			return 0;
		if(col > width - 1)
			return 0;
		if(row > height - 1)
			return 0;
		return data[col + row * width];
	}
}
