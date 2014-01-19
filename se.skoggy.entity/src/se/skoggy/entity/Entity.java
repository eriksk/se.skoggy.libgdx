package se.skoggy.entity;

import java.util.ArrayList;
import java.util.List;

import se.skoggy.entity.Transform;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Entity implements Disposable{

	protected TextureRegion textureRegion;
	public Transform transform;
	public Vector2 origin;
	protected List<EntityBehavior> behaviors;
	protected boolean flipX, flipY;
	public Color color;
	private Rectangle region;

	public Entity(TextureRegion texture){
		textureRegion = texture;
		transform = new Transform();
		origin = new Vector2();
		color = new Color(1f, 1f, 1f, 1f);
		flipX = false;
		region = new Rectangle();
		setSource(0,  0,  texture.getTexture().getWidth(),  texture.getTexture().getHeight());

		behaviors = new ArrayList<EntityBehavior>();

		// default flip state
		flipY = true;
		flipX = false;
	}

	public void addBehavior(EntityBehavior behavior){
		behaviors.add(behavior);
	}

	public void teleport(float x, float y) {
		transform.position.x = x;
		transform.position.y = y;
	}

	public void setFlip(boolean x, boolean y){
		this.flipX = x;
		this.flipY = y;
		// flip if it is not already the same
		x = textureRegion.isFlipX() != x;
		y = textureRegion.isFlipY() != y;
		textureRegion.flip(x,  y);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFlipX() {
		return flipX;
	}

	@Override
	public void dispose() {
	}

	public void setSource(int x, int y, int width, int height){
		textureRegion.setRegion(x, y, width, height);
		origin.x =  width * 0.5f;
		origin.y = height * 0.5f;
		textureRegion.flip(flipX,  true);
		region.x = x;
		region.y = y;
		region.width = width;
		region.height = height;
	}

	public Rectangle getSource() {
		return region;
	}

	public void update(float dt){
		for (EntityBehavior b : behaviors) {
			b.Update(dt, this);
		}
	}

	public void draw(SpriteBatch spriteBatch){
		spriteBatch.setColor(color);

		for (EntityBehavior b : behaviors) {
			b.BeforeDraw(spriteBatch, this);
		}

		spriteBatch.draw(
				textureRegion,
				transform.position.x - origin.x,
				transform.position.y - origin.y,
				origin.x,
				origin.y,
				textureRegion.getRegionWidth(),
				textureRegion.getRegionHeight(),
				transform.scale.x,
				transform.scale.y,
				(float)(transform.rotation * 180f / Math.PI));

		for (EntityBehavior b : behaviors) {
			b.AfterDraw(spriteBatch, this);
		}
	}

	public void drawWithoutBehaviors(SpriteBatch spriteBatch, Color colorOverride){
		spriteBatch.setColor(colorOverride);
		spriteBatch.draw(
				textureRegion,
				transform.position.x - origin.x,
				transform.position.y - origin.y,
				origin.x,
				origin.y,
				textureRegion.getRegionWidth(),
				textureRegion.getRegionHeight(),
				transform.scale.x,
				transform.scale.y,
				(float)(transform.rotation * 180f / Math.PI));
	}
}