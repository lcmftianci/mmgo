//游戏主舞台
package com.example.myapplication.WhitePile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
//import com.fxb.whitetile.Constant.GameState;

public class GameStage extends Stage{

    //Pool<Image> poolWhite;
    //Pool<Image> poolBlack;
    
    Pool<Block> poolBlock;
    int topIndex;
    float transferHeight;
    float imgHeight;
    Group groupImg;
    
    Drawable drawWhite;
    Drawable drawGray;
    
    WhiteTileGameScreen gameScreen;
    float speed;
    int count;
    
    int lastIndex1, lastIndex2;
    
    Block block;
    
    public GameStage(WhiteTileGameScreen screen0){
        //poolWhite = Pools.get( Image.class );
        //poolBlack = Pools.get( Image.class );
        gameScreen = screen0;    
        poolBlock = Pools.get( Block.class );
        imgHeight = (this.getHeight()-9)/4;
        
        groupImg = new Group();
        this.addActor( groupImg );
        
        drawGray = WhiteTile.skin.newDrawable( "white", Color.GRAY );
        drawWhite = WhiteTile.skin.newDrawable( "white" );
        
        //Block.gameScreen = gameScreen;
        
        Clear();
    }
    
    public void Clear(){
        topIndex = 5;
        this.getCamera().translate( 0, -transferHeight, 0 );
        transferHeight = 0;        
        speed = Constant.initSpeed*Constant.level;
        count = 0;
        
        groupImg.clear();
        poolBlock.clear();
                
        lastIndex1 = lastIndex2 = -1;
        
        for( int j=0; j<topIndex; ++j ){
            AddBlock( j );
        }
    }
    
    
    public void AddBlock( int yIndex ){
        int index;
        do{
            index = MathUtils.random( 98 )%3;
        }while( lastIndex1==lastIndex2 && index==lastIndex1 );
        
        lastIndex2 = lastIndex1;        
        lastIndex1 = index;
        
        for( int i=0; i<3; ++i ){
            Block block = poolBlock.obtain();
            block.Clear();
            if( i == index ){
                block.color = Color.GRAY;
                block.setDrawable( drawGray );
            }else{
                block.color = Color.WHITE;
                block.setDrawable( drawWhite );
            }
                    
            //blockTran = block;
            //block.gameScreen = gameScreen;
            
            block.setSize( (this.getWidth()-12)/3, imgHeight );
            block.setPosition( 3+3*i+i*block.getWidth(), (yIndex+2)*(imgHeight+3) );
            groupImg.addActor( block );
        }
    }
    
    
    
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        super.draw();
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub
            
        count++;
        if( count > 60 ){
            count = 0;
            speed += Constant.speedInc*Constant.level;
        }
        
        
        getCamera().translate( 0, speed, 0 );
        transferHeight += speed;
        
        
        /*
        for( int i=0; i<groupImg.getChildren().size; ++i ){
            Block block = (Block)groupImg.getChildren().items[i];
            if( block.getY() < transferHeight && block.color==Color.GRAY && !block.isTouch ){
                Constant.state = Constant.GameState.game_preover;
            }
        }
        */
                
        boolean bIsDis = false;
        for( int i=0; i<groupImg.getChildren().size; ++i ){
            Block block = (Block)groupImg.getChildren().items[i];
            
            if( block.color==Color.WHITE && block.isTouch ){
                Constant.state = Constant.GameState.game_preover;
                //gameScreen.overStage = new OverStage();
                gameScreen.overStage.Show();
                Gdx.input.setInputProcessor( gameScreen.overStage );
            }
            
            if( block.getTop() < transferHeight )
            {
                if( block.color==Color.GRAY && !block.isTouch ){
                    Constant.state = Constant.GameState.game_preover;
                    //gameScreen.overStage = new OverStage();
                    gameScreen.overStage.Show();
                    Gdx.input.setInputProcessor( gameScreen.overStage );
                }
                
                groupImg.removeActor( block );
                poolBlock.free( block );
                if( !bIsDis ){
                    bIsDis = true;                    
                }
            }
        }
        if( bIsDis ){
            AddBlock( topIndex );
            topIndex++;
        }
                
        super.act();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
        super.dispose();
    }

    
}