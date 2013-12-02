package se.skoggy.scenes;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager {

	public static final int TRANSITION_IN = 0;
	public static final int TRANSITION_OUT = 1;
	public static final int ACTIVE = 2;
	public static final int GOTO_NEXT_SCENE = 3;

	protected List<Scene> scenes;
	protected Scene activeScene;
	protected int state;
	protected float currentProgress;

	public SceneManager() {
		scenes = new ArrayList<Scene>();
	}

	public boolean hasActiveScene(){
		return !scenes.isEmpty();
	}

	public boolean hasAnyScenes(){
		return !scenes.isEmpty();
	}

	public boolean isTransitioningOut(){
		return state == TRANSITION_OUT;
	}

	public void pushScene(Scene scene){
		if(hasActiveScene()){
			if(state != TRANSITION_OUT){
				setState(TRANSITION_OUT);
			}
		}else{
			activeScene = scene;
		}
		scenes.add(scene);
		scene.setSceneManager(this);
		scene.initCam();
	}

	public void popScene(){
		if(hasActiveScene()){
			setState(TRANSITION_OUT);
		}
	}

	protected void setState(int state){
		this.state = state;
		switch (state) {
			case ACTIVE:
				// nothing
			break;
			case TRANSITION_IN:
			break;
			case TRANSITION_OUT:
			break;
			case GOTO_NEXT_SCENE:
				if(hasAnyScenes()){
					activeScene = scenes.get(0);
					setState(TRANSITION_IN);
				}
				break;
		}
		currentProgress = 0f;
	}

	protected void afterTransitionIn(){
		setState(ACTIVE);
	}
	protected void afterTransitionOut(){
		removeLastScene();
		setState(TRANSITION_IN);
	}

	protected void setLastSceneToCurrentScene() {
		if(!scenes.isEmpty()){
			activeScene = scenes.get(scenes.size() - 1);
			setState(TRANSITION_IN);
		}
	}

	protected void removeLastScene(){
		if(!scenes.isEmpty()){
			scenes.remove(scenes.size() - 1);
			activeScene = null;
		}
	}

	protected void removeActiveScene() {
		scenes.remove(activeScene);
		activeScene = null;
	}

	public void update(float dt) {
		if(hasActiveScene()){
			currentProgress += dt;
			switch (state) {
				case ACTIVE:
					activeScene.update(dt);
				break;
				case TRANSITION_IN:
					if(currentProgress / activeScene.transitionInDuration() > 1f){
						setState(ACTIVE);
					}else{
						activeScene.updateTransitionIn(dt, currentProgress / activeScene.transitionInDuration());
					}

				break;
				case TRANSITION_OUT:
					if(currentProgress / activeScene.transitionOutDuration() > 1f){
						removeActiveScene();
						setState(GOTO_NEXT_SCENE);
					}else{
						activeScene.updateTransitionOut(dt, currentProgress / activeScene.transitionOutDuration());
					}

				break;
			}
		}
	}

	public void draw(SpriteBatch sb){
		if(hasActiveScene()){
			switch (state) {
				case ACTIVE:
					activeScene.draw(sb);
				break;
				case TRANSITION_IN:
					activeScene.drawTransitionIn(sb, currentProgress / activeScene.transitionInDuration());
				break;
				case TRANSITION_OUT:
					activeScene.drawTransitionOut(sb, currentProgress / activeScene.transitionOutDuration());
				break;
			}
		}
	}
}
