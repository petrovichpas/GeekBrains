package com.star.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.star.app.game.GameController;
import com.star.app.game.WorldRenderer;
import com.star.app.screen.utils.Assets;

public class GameScreen extends AbstractScreen {
    private GameController gameController;
    private WorldRenderer worldRenderer;
    private BitmapFont font24;
    private Stage stage;


    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void show() {
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.GAME);
        this.gameController = new GameController();
        this.worldRenderer = new WorldRenderer(gameController, batch);


        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        this.font24 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");

        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        textButtonStyle.font = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");

        skin.add("simpleSkin", textButtonStyle);

        Button btnPAUSE = new TextButton("PAUSE", textButtonStyle);
        Button btnMENU = new TextButton("MENU", textButtonStyle);
        btnPAUSE.setPosition(945, 625);
        btnMENU.setPosition(945, 540);

        btnPAUSE.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //Как-то остановить передвижение объектов и добавить кнопку возврата к игре
                //ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.PAUSE);
            }
        });

        btnMENU.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
            }
        });

        stage.addActor(btnPAUSE);
        stage.addActor(btnMENU);
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        gameController.update(delta);
        worldRenderer.render();
        stage.draw();
    }

    @Override
    public void dispose() {
        gameController.dispose();
    }
}
