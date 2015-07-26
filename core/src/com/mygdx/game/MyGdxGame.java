package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture img2;
	Rectangle rect;
	Rectangle rect2;
	OrthographicCamera camera;
	BitmapFont font;
	long lastUpdateTime;
	int score = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("badlogic.jpg"));
		img2 = new Texture(Gdx.files.internal("j_pic.gif"));
		rect = new Rectangle();
		rect2 = new Rectangle();
		rect.x = 500;
		rect.y = 500;
		rect.width = img.getWidth();
		rect.height = img.getHeight();
		rect2.x = Gdx.graphics.getWidth()/2;
		rect2.y = Gdx.graphics.getHeight()/2;
		rect2.width = img2.getWidth();
		rect2.height = img2.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		font = new BitmapFont();
		font.setColor(Color.BLUE);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, rect.x, rect.y);
		batch.draw(img2, rect2.x, rect2.y);

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			rect.x = touchPos.x - rect.width / 2;
			rect.y = touchPos.y - rect.height / 2;
		}

		if(TimeUtils.nanoTime() - lastUpdateTime > 500000000){
			lastUpdateTime = TimeUtils.nanoTime();
			rect2.x = (float) (Math.random() * Gdx.graphics.getWidth());
			rect2.y = (float) (Math.random() * Gdx.graphics.getHeight());
		}

		if(rect.overlaps(rect2)){
			score++;
		}
		font.draw(batch, "" + score, 100, 100);
		batch.end();
	}
}
