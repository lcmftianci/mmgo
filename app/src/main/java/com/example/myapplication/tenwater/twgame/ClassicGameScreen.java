package com.example.myapplication.tenwater.twgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.example.myapplication.tenwater.TenWaterGame;
import com.example.myapplication.tenwater.twactor.BottomGroup;
import com.example.myapplication.tenwater.twactor.MiddleGroup;
import com.example.myapplication.tenwater.twactor.TopGroup;
import com.example.myapplication.tenwater.twutils.AssetsManager;
//import com.libgdx.tenwater.TenWaterGame;
//import com.libgdx.tenwater.actor.BottomGroup;
//import com.libgdx.tenwater.actor.MiddleGroup;
//import com.libgdx.tenwater.actor.TopGroup;
//import com.libgdx.tenwater.utils.AssetsManager;

public class ClassicGameScreen extends AbstractBaseScreen {
	private static final String TAG = ClassicGameScreen.class.getSimpleName();
	
    private Stage stage;
    private Image bgImg;
    
    FileHandle filepath;
    TopGroup topGroup;
    BottomGroup bottomGroup;
    MiddleGroup middleGroup;
    Window nextWindow;
    Window failedWindow;
    
    public ClassicGameScreen() { }
    
    public ClassicGameScreen(TenWaterGame game, FileHandle filepath) {
        super(game);
        this.filepath = filepath;
    }

    @Override
    public void show() {
        
        initGameData();
    }
    
    private void initGameData() {
       stage = new Stage(game.viewport, game.batch);
       stage.setDebugAll(game.debug);
       Gdx.input.setInputProcessor(stage);
        
       bgImg = new Image(AssetsManager.assetsManager.assetsBg.game_bgTxt);
       bgImg.setFillParent(true);
        
       // Top°´Å¥ ¼ÇÂ¼·ÖÊý
       topGroup = new TopGroup(game);
       float topGroupY = game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBtn.levelBtnTxt.getRegionHeight() - 20f;
       topGroup.setY(topGroupY);
       
       bottomGroup = new BottomGroup(game);
       // Í³Ò»ÉèÖÃButtomGroupÀïÃæActorµÄY×ø±ê
       bottomGroup.setY(15f);
       Gdx.app.log(TAG, "ButtomGroup(x,y)=(" + bottomGroup.getX() +","+ bottomGroup.getY() + 
		   		")  ButtomGroup width=" + bottomGroup.getWidth() + " Height=" + bottomGroup.getHeight());
       
       // ÓÎÏ·µÄÖÐ¼ä²¿·Ö£¬°üÀ¨Íø¸ñ£¬Ë®µÎµÄ
       middleGroup = new MiddleGroup(game, filepath);
//       ÎªÁË·½±ã»ñÈ¡×ø±ê£¬½«×ø±ê·ÅÈëMiddleGroupÀïÃæ
//       float middleGroupX = game.VIRTUAL_WORLD_WIDTH / 2 - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() / 2;
//       float middleGroupY = (game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1) + 20;
//       middleGroup.setPosition(middleGroupX, middleGroupY);
//       Gdx.app.log(TAG, "gridImage X=" + middleGroup.getGridImage().getX() + "  gridY=" + middleGroup.getY());
       
       // ÉèÖÃµ±Ç°¹Ø¿¨Êý
       topGroup.setCurrentLevel("" + middleGroup.getCurLevel());
       
       // ÉèÖÃresetButtonÊÂ¼þ
       bottomGroup.getResetButton().addListener(new ClickListener() {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            middleGroup.resetGameData();
        }
           
       });
       
       
       stage.addActor(bgImg);
       stage.addActor(middleGroup);
       stage.addActor(topGroup);
       stage.addActor(bottomGroup);
       
       // ³õÊ¼»¯ÏÂÒ»¹Ø´°¿Ú
       initNextGameWindow();
       
       // ³õÊ¼»¯ÌôÕ½Ê§°ÜµÄ´°¿Ú
       initFailedGameWindow();
       
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        stage.act(Math.min(delta, 1/60f));
        stage.draw(); 
        
        if (!nextWindow.isVisible() && middleGroup.checkGameOver()) {
            nextWindow.setVisible(true);
        }
        
        if (TopGroup.getRemainWaterNum() == 0) {
            failedWindow.setVisible(true);
        }
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        super.hide();
    }
    
    public void initNextGameWindow() {
        // ´³¹Ø³É¹¦£¬³öÏÖ¶Ô»°¿ò
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(
                                new TextureRegion(AssetsManager.assetsManager.assetsBg.classic_winFgTxt));
        windowStyle.titleFont = AssetsManager.assetsManager.assetsfont.defaultFont;
        nextWindow  = new Window("", windowStyle);
        nextWindow.setModal(true);
        nextWindow.setX(middleGroup.getGridImage().getX() + middleGroup.getGridImage().getWidth() / 2 - nextWindow.getWidth() /2);
        nextWindow.setY(middleGroup.getGridImage().getY() + middleGroup.getGridImage().getHeight() / 2 - nextWindow.getHeight() /2);
        nextWindow.setVisible(false);
        nextWindow.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextWindow.setVisible(false);
                middleGroup.addCurLevel();
                middleGroup.resetGameData();
                topGroup.setCurrentLevel("" + middleGroup.getCurLevel());
                TopGroup.setRemainWaterNum(TopGroup.getRemainWaterNum() + 1);
            }
            
        });
        
        stage.addActor(nextWindow);
    }

    public void initFailedGameWindow() {
        // µ±Ê£ÓàË®µÎÊýÎª0Ê±£¬ÓÎÏ·Ê§°Ü£¬·µ»Ø²Ëµ¥¼ü
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(
                                new TextureRegion(AssetsManager.assetsManager.assetsBg.classic_loseFgTxt));
        windowStyle.titleFont = AssetsManager.assetsManager.assetsfont.defaultFont;
        failedWindow  = new Window("", windowStyle);
        failedWindow.setModal(true);
        failedWindow.setX(middleGroup.getGridImage().getX() + middleGroup.getGridImage().getWidth() / 2 - nextWindow.getWidth() /2);
        failedWindow.setY(middleGroup.getGridImage().getY() + middleGroup.getGridImage().getHeight() / 2 - nextWindow.getHeight() /2);
        failedWindow.setVisible(false);
        failedWindow.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
            
        });
        
        stage.addActor(failedWindow);
    }
}
