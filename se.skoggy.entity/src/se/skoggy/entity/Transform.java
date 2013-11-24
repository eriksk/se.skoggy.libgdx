package se.skoggy.entity;

import com.badlogic.gdx.math.Vector2;

public class Transform {

	public Vector2 position, velocity, scale;
	public float rotation;
	
	public Transform() {
		position = new Vector2();
		scale = new Vector2(1f, 1f);
		velocity = new Vector2();
		rotation = 0f;
	}
}
