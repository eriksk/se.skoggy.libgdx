package se.skoggy.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class ContentManager implements Disposable{

	String contentRoot;
	boolean flipYOnSprites;
	
	public ContentManager(String contentRoot, boolean flipYOnSprites){
		this.contentRoot = contentRoot;
		this.flipYOnSprites = flipYOnSprites;
		if(!this.contentRoot.endsWith("/") && this.contentRoot != ""){
			this.contentRoot += "/";
		}
	}
	
	public TextureRegion loadTexture(String name){
		TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal(contentRoot + name + ".png")));
		// TODO: Doesn't really work, if region is set later this will be overridden, FIX
		texture.flip(false, flipYOnSprites);
		return texture;
	}
	
	public TextureRegion[] loadTextureSheet(String name, int cols, int rows){
		TextureRegion tex = new TextureRegion(new Texture(Gdx.files.internal(contentRoot + name + ".png")));
		TextureRegion[][] texes = tex.split(cols,  rows);
		int count = 0;
		TextureRegion[] textures = new TextureRegion[texes.length * texes[0].length];
		for (int i = 0; i < texes.length; i++) {
			for (int j = 0; j < texes[i].length; j++) {
				textures[count] = texes[j][i];
				count++;
			}
		}
		return textures;
	}
	
	@Override
	public void dispose() {
		// TODO: cache all textures and dispose here
	}
}
