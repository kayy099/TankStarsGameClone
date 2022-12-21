package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import static com.badlogic.drop.Constants.PPM;

public class GameScreen implements Screen {
	final TankStars game;
	public ImageButton pauseMenu;
	private Texture tank1;
	private Texture tank2;
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
	private Body player1_box;
	private Body player2_box;
	private SpriteBatch batch;
	int turn = 0;
	public GameScreen(final TankStars game) {
		this.game = game;
		//backgroundImage = new Texture(Gdx.files.internal("terrain_game(1).jpg"));
		//backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 567);

		warMusic = Gdx.audio.newMusic(Gdx.files.internal("war-full.wav"));
		warMusic.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		stage = new Stage();
		map = new TmxMapLoader().load("final_path.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//        gamePort = new FitViewport(1000, 250,camera);
		gamePort = new StretchViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,camera);
		camera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2.5f,0);
		world = new World(new Vector2(0,0),false);
		b2dr = new Box2DDebugRenderer();
		player1_box = createBox(150,150,55,32,false);
		player2_box = createBox(350,150,55,32,false);
		batch = new SpriteBatch();
		tank1 = new Texture("newtank3.png");
		tank2 = new Texture("newtank1flip.png");
		TiledObjectUtil.parseTiledObjectLayer(world,map.getLayers().get("ground").getObjects());
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		update(Gdx.graphics.getDeltaTime());
		hud = new Hud(game.batch);
//		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
//		camera.update();
		renderer.render();
		renderer.setView(camera);
		gamePort.apply();
        batch.begin();
//		batch.draw(tex,100,100,50,50);
		batch.draw(tank1,player1_box.getPosition().x-25 ,player1_box.getPosition().y-25,50,50);
		batch.draw(tank2,player2_box.getPosition().x-25 ,player2_box.getPosition().y-25,50,50);
		batch.end();
		b2dr.render(world, camera.combined);

	}


	@Override
	public void show() {
		//warMusic.play();
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

//	public void handleInput (float dt){
//		if(Gdx.input.isTouched())
//			camera.position.x += 100*dt;
//	}
	public void update(float dt){
		world.step(1/60f, 6,2);
		cameraUpdate(dt);
		inputUpdate(dt);
		batch.setProjectionMatrix(camera.combined);
//		handleInput(dt);
//		camera.update();
//		renderer.setView(camera);

	}
	public void inputUpdate(float dt){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			if(turn==0){
				turn=1;
			}
			else if(turn==1){
				turn=0;
			}
		}
		if(turn==0){
			int horizontalForce1 = 0;
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				horizontalForce1-=1;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				horizontalForce1+=1;
			}
			player1_box.setLinearVelocity(horizontalForce1*10, player1_box.getLinearVelocity().y);
		}
		else if(turn==1){
			int horizontalForce2 = 0;
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				horizontalForce2-=1;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				horizontalForce2+=1;
			}
			player2_box.setLinearVelocity(horizontalForce2*10, player1_box.getLinearVelocity().y);
		}



	}
	public void cameraUpdate(float delta){
//		Vector3 position = camera.position;
//		position.x = player.getPosition().x;
//		position.y = player.getPosition().y;
//		camera.position.set(position);
//		camera.update();
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
		map.dispose();
		renderer.dispose();
		b2dr.dispose();
		world.dispose();
	}
	public Body createBox(int x, int y,int width, int height, boolean isStatic){
		Body pBody;
		BodyDef def = new BodyDef();
		if(isStatic)
			def.type = BodyDef.BodyType.StaticBody;
		else
			def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x,y);
		def.fixedRotation = true;
		pBody = world.createBody(def);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2,height/2);
		pBody.createFixture(shape,1.0f);
		shape.dispose();
		return pBody;
	}

}
