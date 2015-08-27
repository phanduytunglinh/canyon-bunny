package com.packtpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.packtpub.libgdx.canyonbunny.util.CameraHelper;

public class WorldController {

	private static final String TAG = WorldController.class.getName();
	public Sprite[] testSprites;
	public int selectedSprite;

	public WorldController() {
		init();
	}

	private void init() {
		initTestObjects();
	}

	public void update(float deltaTime) {
		updateTestObjects(deltaTime);
	}

	private void moveSelectedSprite(float x, float y) {
		testSprites[selectedSprite].translate(x, y);
	}

	private void initTestObjects() {
		testSprites = new Sprite[5];
		int width = 32;
		int height = 32;
		Pixmap pixmap = createProceduralPixmap(width, height);
		Texture texture = new Texture(pixmap);
		for (int i = 0; i < testSprites.length; i++) {
			Sprite spr = new Sprite(texture);
			spr.setSize(1, 1);
			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			spr.setPosition(randomX, randomY);
			testSprites[i] = spr;
		}
		selectedSprite = 0;
	}

	private void updateTestObjects(float deltaTime) {
		float rotation = testSprites[selectedSprite].getRotation();
		rotation = (rotation + 90 * deltaTime) % 360;
		testSprites[selectedSprite].setRotation(rotation);
	}

	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		// Draw a yellow-colored X shape on square
		pixmap.setColor(1, 1, 0, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		// Draw a cyan-colored border around square
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}

}
