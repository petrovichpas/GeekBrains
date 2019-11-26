package com.star.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;
import com.star.app.screen.utils.Assets;

public class WorldRenderer {
    private GameController gc;
    private SpriteBatch batch;
    private BitmapFont font32;
    private StringBuilder scoreBuilder, hpBuilder;

    public WorldRenderer(GameController gc, SpriteBatch batch) {
        this.gc = gc;
        this.batch = batch;
        this.font32 = Assets.getInstance().getAssetManager().get("fonts/font32.ttf", BitmapFont.class);
        this.scoreBuilder = new StringBuilder();
        this.hpBuilder = new StringBuilder();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        gc.getBackground().render(batch);
        gc.getBulletController().render(batch);

        gc.getAsteroidController().render(batch);
        scoreBuilder.clear();
        scoreBuilder.append("SCORE: ").append(gc.getHero().getScoreView());
        font32.draw(batch, scoreBuilder, 20, 700);

        gc.getHero().render(batch);
        hpBuilder.clear();
        hpBuilder.append("LIVES: ").append(gc.getHero().getHpView());
        font32.draw(batch, hpBuilder, 1130, 700);
        batch.end();
    }
}
