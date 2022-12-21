package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.physics.box2d.Body;

public class GameScreen implements Screen {
	final TankStars game;
	public ImageButton pauseMenu;
	private Texture backgroundImage;
	private TextureRegion backgroundTexture;
	private Stage stage;
	Music warMusic;
	private OrthographicCamera camera;
	private Viewport gamePort;
	private Hud hud;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private World world;
	private Box2DDebugRenderer b2dr;
	private Body player;
	public GameScreen(final TankStars game) {
		this.game = game;
		//backgroundImage = new Texture(Gdx.files.internal("terrain_game(1).jpg"));
		//backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 567);

		warMusic = Gdx.audio.newMusic(Gdx.files.internal("war-full.wav"));
		warMusic.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		stage = new Stage();
		map = new TmxMapLoader().load("AP_trail.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//        gamePort = new FitViewport(1000, 250,camera);
		gamePort = new StretchViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,camera);
		camera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2.5f,0);
		world = new World(new Vector2(0,0),false);
		b2dr = new Box2DDebugRenderer();
		player = createPlayer();

	}

	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0, 1);
		update(Gdx.graphics.getDeltaTime());
		hud = new Hud(game.batch);
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
//        game.batch.setProjectionMatrix(camera.combined);
//
//        game.batch.begin();
//        game.batch.draw(backgroundTexture,0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        game.batch.end();

		stage.getBatch().setProjectionMatrix(camera.combined);
		//stage.getBatch().begin();
		//stage.getBatch().draw(backgroundTexture,0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//stage.getBatch().end();
		stage.draw();
		renderer.render();
		renderer.setView(camera);
		gamePort.apply();
		b2dr.render(world, camera.combined);

		// process user input
//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            bucket.x = touchPos.x - 64 / 2;
//        }
//        if (Gdx.input.isKeyPressed(Keys.LEFT))
//            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Keys.RIGHT))
//            bucket.x += 200 * Gdx.graphics.getDeltaTime();
//
//        // make sure the bucket stays within the screen bounds
//        if (bucket.x < 0)
//            bucket.x = 0;
//        if (bucket.x > 800 - 64)
//            bucket.x = 800 - 64;
//
//        // check if we need to create a new raindrop
//        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
//            spawnRaindrop();
//
//        // move the raindrops, remove any that are beneath the bottom edge of
//        // the screen or that hit the bucket. In the later case we increase the
//        // value our drops counter and add a sound effect.
//        Iterator<Rectangle> iter = raindrops.iterator();
//        while (iter.hasNext()) {
//            Rectangle raindrop = iter.next();
//            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
//            if (raindrop.y + 64 < 0)
//                iter.remove();
//            if (raindrop.overlaps(bucket)) {
//                dropsGathered++;
//                dropSound.play();
//                iter.remove();
//            }
//        }
	}


	@Override
	public void show() {
		warMusic.play();
//        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), game.batch);
//        Gdx.input.setInputProcessor(stage);
//        Texture pauseMenuTexture = new Texture(Gdx.files.internal("icons8-xbox-menu-96.png"));
//        TextureRegion pauseMenuTextureRegion = new TextureRegion(pauseMenuTexture);
//        TextureRegionDrawable pauseMenuTextureRegionDrawable = new TextureRegionDrawable(pauseMenuTextureRegion);
//        pauseMenu = new ImageButton(pauseMenuTextureRegionDrawable);
//        pauseMenu.setPosition(13,416);
//        pauseMenu.setSize(51,51);
//        pauseMenu.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                ((Game)Gdx.app.getApplicationListener()).setScreen(new PauseScreen(game));
//                dispose();
//            }
//        });
//        stage.addActor(pauseMenu);
	}

	public void handleInput (float dt){
		if(Gdx.input.isTouched())
			camera.position.x += 100*dt;
	}
	public void update(float dt){
		world.step(1/60f, 6,2);
		handleInput(dt);
		camera.update();
		renderer.setView(camera);
		cameraUpdate(dt);
	}
	public void cameraUpdate(float delta){
		Vector3 position = camera.position;

	}

	@Override
	public void resize (int width, int height){
		gamePort.update(width, height);
	}


	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		warMusic.dispose();
		map.dispose();
		renderer.dispose();
		b2dr.dispose();
		world.dispose();
	}
	public Body createPlayer(){
		Body pBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(0,0);
		def.fixedRotation = true;
		pBody = world.createBody(def);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32/2,32/2);
		pBody.createFixture(shape,1.0f);
		shape.dispose();
		return pBody;
	}

}
