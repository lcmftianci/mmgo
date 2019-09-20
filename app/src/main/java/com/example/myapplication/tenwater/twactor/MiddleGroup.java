package com.example.myapplication.tenwater.twactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.example.myapplication.tenwater.TenWaterGame;
import com.example.myapplication.tenwater.twactor.base.BaseGroup;
import com.example.myapplication.tenwater.twutils.AssetsManager;
import com.example.myapplication.tenwater.twutils.XMLParser;
//import com.libgdx.tenwater.TenWaterGame;
//import com.libgdx.tenwater.actor.base.BaseGroup;
//import com.libgdx.tenwater.utils.AssetsManager;
//import com.libgdx.tenwater.utils.XMLParser;

public class MiddleGroup extends BaseGroup {

	// Ë®µÎµÄÐÐÁÐÊý
	private static final int WATER_ROW_NUM = 6;
	private static final int WATER_COL_NUM = 6;
	
	// µ±Ç°¹Ø¿¨
	private int curLevel = 0;
	private int keyFrameIndex = 0;
	
	SnapshotArray<Actor> actorArray;
	
	Rectangle waterRec;
    Rectangle smallWaterRec;
    
    XMLParser xmlParser;
	Image gridImg;
	WaterActor[][] waterActor = new WaterActor[WATER_ROW_NUM][WATER_COL_NUM];
	
	public MiddleGroup(TenWaterGame game) {
		super(game);
		
		xmlParser = new XMLParser();
        xmlParser.initData(Gdx.files.internal("classic/classic.xml"));
		init();
	}
	
	public MiddleGroup(TenWaterGame game, FileHandle filepath) {
        super(game);
        
        xmlParser = new XMLParser();
        xmlParser.initData(filepath);
        init();
    }

	public void init() {
	    this.clear();
	    
	    setSize(getGame().VIRTUAL_WORLD_WIDTH, getGame().VIRTUAL_WORLD_HEIGHT);
	    
	    actorArray = new SnapshotArray<Actor>();
	    waterRec = new Rectangle();
	    smallWaterRec = new Rectangle();
	    
		gridImg = new Image(AssetsManager.assetsManager.assetsBg.cellTxt);
		float gridImgX = getGame().VIRTUAL_WORLD_WIDTH / 2 - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() / 2;
	    float gridImgY = (getGame().VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1) + 20;
	    gridImg.setPosition(gridImgX, gridImgY);
		
		resetGameData();
	}
	
	public void resetGameData() {
	    this.clear();
	    actorArray.clear();
	    
	    addActor(gridImg);
	    
	 // ´´½¨ËùÓÐµÄË®µÎ
        for (int row = 0; row < WATER_ROW_NUM; row++) {
            for (int col = 0; col < WATER_COL_NUM; col++) {
                keyFrameIndex = xmlParser.levelData[curLevel].getZappers()[row * 6 + col];
                keyFrameIndex -= 1;
                waterActor[row][col] = new WaterActor(this);
                waterActor[row][col].setKeyFrameIndex(keyFrameIndex);
                waterActor[row][col].setOrigin(Align.center);
                addActor(waterActor[row][col]);
            }
        }
        
        // Ë®Öé´óÐ¡
        float waterWidth = waterActor[0][0].getWidth();
        float waterHeight = waterActor[0][0].getHeight();
        
        // ¼ÆËãË®ÖéÖ®¼äµÄ¼äÏ¶´óÐ¡
        float horizontalInterval = (gridImg.getWidth() - waterWidth * WATER_ROW_NUM) / (WATER_ROW_NUM + 1);
        float verticalInterval = (gridImg.getHeight() - waterHeight * WATER_COL_NUM) / (WATER_COL_NUM + 1);
        
        // ¾ùÔÈµÄÅÅÁÐË®Öé
        float waterY = 0;
        for (int row = 0; row < WATER_ROW_NUM; row++) {
            waterY = 0.5f + gridImg.getY() + gridImg.getHeight() - (verticalInterval + waterHeight) * (row + 1);
            for (int col = 0; col < WATER_COL_NUM; col++) {
                waterActor[row][col].setX(0.5f + gridImg.getX() + horizontalInterval + (waterWidth + horizontalInterval) * col);
                waterActor[row][col].setY(waterY);
            }
        }
	}
	
	/**
	 * ½ö×÷²âÊÔ´úÂëÓÃ£¬²é¿´gridImageµÄ×ø±ê
	 */
	public Image getGridImage() {
		return gridImg;
	}
	
	public int getCurLevel() {
        return curLevel + 1;
    }
	
	public void addCurLevel() {
	    curLevel++;
	}
	
    @Override
    public void act(float delta) {
        super.act(delta);
        
        actorArray = this.getChildren();
        for (Actor actor : actorArray) {
            if (actor instanceof SmallWaterActor && actor.isVisible()) {
                checkCollision(actor);
            }
        }
    }

    private synchronized void checkCollision(Actor actor) {
        for (int row = 0; row < WATER_ROW_NUM; row++) {
            for (int col = 0; col < WATER_COL_NUM; col++) {
                if (waterActor[row][col].isVisible()) {
                    smallWaterRec.setPosition(actor.getX(), actor.getY());
                    smallWaterRec.setSize(actor.getWidth(), actor.getHeight());
                    waterRec.setPosition(waterActor[row][col].getX(), waterActor[row][col].getY());
                    waterRec.setSize(waterActor[row][col].getWidth(), waterActor[row][col].getHeight());
                    if (waterRec.overlaps(smallWaterRec)) {
                        actor.setVisible(false);
                        waterActor[row][col].addKeyFrameIndex();
                    }
                }
                
            }
        }
    }
    
    public boolean checkGameOver() {
        boolean isGameOver = true;
        for (int row = 0; row < WATER_ROW_NUM; row++) {
            for (int col = 0; col < WATER_COL_NUM; col++) {
                if (waterActor[row][col].isVisible()) {
                    isGameOver = false;
                    break;
                }
            }
            
            if (!isGameOver) {
                break;
            }
        }
        
        return isGameOver;
    }
    
}
