package se.skoggy.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class Camera2D extends OrthographicCamera{

	Rectangle area;
	private float width, height;

	public Camera2D(float width, float height, Rectangle area) {
		super(width, height);
		setToOrtho(true, width, height);
		this.area = area;
		this.width = width;
		this.height = height;
	}

	public float toWorldX(float localX){
		return position.x - ((width * 0.5f)* zoom) + localX * zoom;
	}

	public float toWorldY(float localY){
 		return position.y - ((height * 0.5f) * zoom) + localY * zoom;
	}

	@Override
	public void update() {
		// boundaries
		if(area != null){
			float halfScreenHeight = (area.height * zoom) * 0.5f;
			float halfScreenWidth = (area.width * zoom) * 0.5f;

			if(position.x - halfScreenWidth < area.x){
				position.x = area.x + halfScreenWidth;
			}
			if(position.y - halfScreenHeight < area.y){
				position.y = area.y + halfScreenHeight;
			}
			if(position.x + halfScreenWidth > area.x + area.width){
				position.x = area.x + area.width - halfScreenWidth;
			}
			if(position.y + halfScreenHeight > area.y + area.height){
				position.y = area.y + area.height - halfScreenHeight;
			}
		}
		super.update();
	}
}
