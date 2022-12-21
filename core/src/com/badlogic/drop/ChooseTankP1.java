package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ChooseTankP1 implements Screen {
    final TankStars game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    private Stage stage;
    protected Skin skin;
    private ImageButton tank1;
    private ImageButton tank2;
    private ImageButton tank3;
    public Button back;
    OrthographicCamera camera;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    BitmapFont font = new BitmapFont();


    public ChooseTankP1(final TankStars game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage();
        backgroundImage = new Texture(Gdx.files.internal("tank_stars_tank1_bg.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 565);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), game.batch);
        Gdx.input.setInputProcessor(stage);

        Table tanksTable = new Table();
        tanksTable.setFillParent(true);
        tanksTable.center();
        tanksTable.columnDefaults(2);
        //tanksTable.setDebug(true);

        //choose1 = new TextButton("Choose Tank", skin);
        back = new TextButton("Back to Main Menu", skin);

        Texture tank1Texture = new Texture(Gdx.files.internal("newtank1.png"));
        TextureRegion tank1TextureRegion = new TextureRegion(tank1Texture);
        TextureRegionDrawable tank1TextureRegionDrawable = new TextureRegionDrawable(tank1TextureRegion);
        tank1 = new ImageButton(tank1TextureRegionDrawable);

        Texture tank2Texture = new Texture(Gdx.files.internal("newtank2.png"));
        TextureRegion tank2TextureRegion = new TextureRegion(tank2Texture);
        TextureRegionDrawable tank2TextureRegionDrawable = new TextureRegionDrawable(tank2TextureRegion);
        tank2 = new ImageButton(tank2TextureRegionDrawable);

        Texture tank3Texture = new Texture(Gdx.files.internal("newtank3.png"));
        TextureRegion tank3TextureRegion = new TextureRegion(tank3Texture);
        TextureRegionDrawable tank3TextureRegionDrawable = new TextureRegionDrawable(tank3TextureRegion);
        tank3 = new ImageButton(tank3TextureRegionDrawable);

        tank1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new ChooseTankP2(game));
            }
        });

        tank2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new ChooseTankP2(game));
            }
        });
        tank3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new ChooseTankP2(game));
            }
        });

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });

        tanksTable.add(tank1).width(200).expandX().padTop(150).height(200);
        tanksTable.add(tank2).width(200).expandX().padTop(150).height(200);
        tanksTable.add(tank3).width(200).expandX().padTop(150).height(200);
        tanksTable.row();
        tanksTable.add();
        tanksTable.add(back);

        //Add table to stage
        stage.addActor(tanksTable);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
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
