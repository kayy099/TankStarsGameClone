package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PauseScreen implements Screen {
    final TankStars game;
    private Stage stage;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public PauseScreen(TankStars game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage();
        backgroundImage = new Texture(Gdx.files.internal("pause_screen_main.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 843, 634);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), game.batch);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 0);
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,200,100,400,350);
        stage.getBatch().end();
        stage.draw();
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
