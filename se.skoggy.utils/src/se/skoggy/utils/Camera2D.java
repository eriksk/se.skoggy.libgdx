package se.skoggy.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Camera2D extends OrthographicCamera{

	Rectangle area;
	private float width, height;
	Vector2 shakeOffset;
	float shakeCurrent, shakeDuration;
	float magnitude;

	public Camera2D(float width, float height, Rectangle area) {
		super(width, height);
		setToOrtho(true, width, height);
		this.area = area;
		this.width = width;
		this.height = height;
		shakeOffset = new Vector2();
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


			shakeCurrent += 16.0f;
			if(shakeCurrent > shakeDuration)
				shakeCurrent = shakeDuration;

			if(isShaking()){
				float shakeProgress = 1f - (shakeCurrent / shakeDuration);
				shakeOffset.x = (-0.5f + Rand.rand()) * magnitude* shakeProgress;
				shakeOffset.y = (-0.5f + Rand.rand()) * magnitude * shakeProgress;

				position.x += shakeOffset.x;
				position.y += shakeOffset.y;
			}
		}

		super.update();
	}

	public boolean isShaking(){
		return shakeDuration != 0f && shakeCurrent / shakeDuration != 1f;
	}

	public void shake(float duration, float magnitude) {
		shakeCurrent = 0f;
		shakeDuration = duration;
		this.magnitude = magnitude;
	}
}
