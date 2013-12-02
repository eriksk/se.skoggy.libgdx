package se.skoggy.scenes;

import se.skoggy.content.ContentManager;
import se.skoggy.utils.Camera2D;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Scene extends Stage{

	protected SceneManager manager;
	protected Camera2D cam;
	protected float width, height;

	public Scene(float width, float height) {
		super(width, height);
		this.width = width;
		this.height = height;
	}

	protected abstract void initCam();
	protected void createCam(Rectangle area){
		setViewport(width, height);
		cam = new Camera2D(width, height, area);
		setCamera(cam);
	}

	void setSceneManager(SceneManager manager){
		this.manager = manager;
	}

	public abstract float transitionInDuration();
	public abstract float transitionOutDuration();
	public abstract boolean isPopup();
	public abstract void load(ContentManager content);
	public void update(float dt){
		cam.update();
	}
	public void updateTransitionIn(float dt, float progress){
		cam.update();
	}
	public void updateTransitionOut(float dt, float progress){
		cam.update();
	}
	public abstract void draw(SpriteBatch sb);
	public abstract void drawTransitionIn(SpriteBatch sb, float progress);
	public abstract void drawTransitionOut(SpriteBatch sb, float progress);
}
