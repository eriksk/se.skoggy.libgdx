package se.skoggy.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class Camera2D extends OrthographicCamera{

	Rectangle area;

	public Camera2D(float width, float height, Rectangle area) {
		super(width, height);
		setToOrtho(true, width, height);
		this.area = area;
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
