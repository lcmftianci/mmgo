package com.example.myapplication.stagepack;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.myapplication.advanceactor.Ball;
import com.example.myapplication.advanceactor.Bomb;
import com.example.myapplication.advanceactor.Brick;
import com.example.myapplication.advanceactor.StaticBall;
import com.example.myapplication.advancestrangely.AdvanceAsserts;
import com.example.myapplication.algorithm.Box2dDetection;
import com.example.myapplication.algorithm.BoxClick;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainScreen extends Stage {

    World world;
    SpriteBatch batch;
    AdvanceAsserts asserts;
    Image background;
    Image floor;
    Texture texture;
    TextureRegion regionBack;
    Bomb bomb;
    Ball ball;
    Brick brick;
    Vector2 brickVec;
    float[] ballxy;
    boolean bDrawBall;
    boolean bOneMoreBall;
    int nOnes;
    Box2dDetection b2d;
    public final List<Ball> balls;
    public final List<StaticBall> staticballs;
    public final List<Brick> bricks;
    Camera cam;
    boolean bBrickCanUpdate;
    private final static String TAG = "MainGameStage";
    private final static int tall = 100;

    int ballWidth;
    int ballHeight;
    int brickWidth;
    int brickHeight;

//    public MainGameStage() {
//        super();
//        texture = new Texture(Gdx.files.internal("atlas/images.png"));
//        regionBack = new TextureRegion(texture, 1,1,480,1000);
//        background = new Image(regionBack);
//        background.setHeight(Gdx.graphics.getHeight());
//        background.setWidth(Gdx.graphics.getWidth());
//        //background.setScale(20,20);
//        //setViewport(new ScreenViewport());
//        //background.setPosition(0,0);
//        this.addActor(background);
//    }

    private void generateAllBall(int nub){
        for(int i =0; i < nub; i++){
            //this.balls.add(new Ball(Gdx.graphics.getWidth()/2, (int)floor.getHeight(), this.asserts));
            //this.addActor(this.balls.get(i));
            //this.staticballs.add(new StaticBall(Gdx));
        }
    }

    private void generareOneBall(World aWorld){
        this.balls.add(new Ball(aWorld,Gdx.graphics.getWidth()/2, (int)floor.getHeight(),ballWidth, ballWidth, this.asserts));
        this.addActor(this.balls.get(this.balls.size()-1));
    }

    public MainScreen(AdvanceAsserts asserts){

        ballWidth = Gdx.graphics.getWidth()/22;
        ballHeight = Gdx.graphics.getHeight()/22;
        brickWidth = Gdx.graphics.getWidth()/6;
        brickHeight = Gdx.graphics.getHeight()/19;

        //创建房子
        this.world = new World(new Vector2(0, Gdx.graphics.getHeight() + Gdx.graphics.getHeight()/19 * tall - Gdx.graphics.getHeight()/19 - 100), true);

        //创建相机
        this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        this.asserts = asserts;
        background = new Image(asserts.regionBack);
        floor = new Image(asserts.regionFloor);

        floor.setWidth(Gdx.graphics.getWidth());
        floor.setHeight(Gdx.graphics.getHeight()/4);
        brickVec = new Vector2();
        bomb = new Bomb(Gdx.graphics.getWidth()/2, (int)floor.getHeight());
        ball = new Ball(world,Gdx.graphics.getWidth()/2, 0, ballWidth, ballWidth, this.asserts);
        b2d = new Box2dDetection();

        this.bricks = new ArrayList<Brick>();
        this.balls = new ArrayList<Ball>();
        this.staticballs = new ArrayList<StaticBall>();

        bDrawBall = false;
        bBrickCanUpdate = false;

        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());
        //background.setScale(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.setPosition(0,0);
        floor.setPosition(0,0);
        this.addActor(background);

        //this.addActor(ball);

        //用来生成随机的100个球球，用来供玩家哭泣
        generateAllBall(tall);
        generareOneBall(this.world);
        bOneMoreBall = false;
        nOnes = 0;

        //brick = new Brick(this.world, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/10*9, brickHeight, brickWidth, 100, asserts.regionBrick);
        //this.addActor(brick);
        //generaAllBrick();

        brickVec.x = Gdx.graphics.getWidth()/6;
        brickVec.y = Gdx.graphics.getHeight()/19;
        generalAllBrick(100, brickWidth, brickWidth);
        this.addActor(bomb);
        this.addActor(floor);

    }

    //在随机的位置上生成一堆砖块供攻击,这个函数需要重写，保证生成数据连续，并且随着高度增加而增加
    /*
    * 根据行来生成数据，每行必须有数据，数据的量可以随机取得
    * */
    public void generaAllBrick(){
        for(int i =0; i < 50; i++){
            if(i < 10)
                this.bricks.add(new Brick(this.world, Gdx.graphics.getWidth()/10 * MathUtils.random(1,5),
                        Gdx.graphics.getHeight()/10 * MathUtils.random(i,10) + (int)floor.getHeight(),
                        Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/10, i+1, this.asserts.regionBrick));
            else if(i >= 10 && i < 20)
                this.bricks.add(new Brick(this.world, Gdx.graphics.getWidth()/10 * MathUtils.random(1,5),
                        Gdx.graphics.getHeight()/10 * MathUtils.random(i,20) + (int)floor.getHeight(),
                        Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/10, i, this.asserts.regionBrick));
            else if(i >= 20 && i < 30)
                this.bricks.add(new Brick(this.world, Gdx.graphics.getWidth()/10 * MathUtils.random(1,5), Gdx.graphics.getHeight()/10 * MathUtils.random(i,30) + (int)floor.getHeight(), Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/10, i, this.asserts.regionBrick));
            else if(i >= 30 && i < 40)
                this.bricks.add(new Brick(this.world, Gdx.graphics.getWidth()/10 * MathUtils.random(1,5), Gdx.graphics.getHeight()/10 * MathUtils.random(i,40) + (int)floor.getHeight(), Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/10, i, this.asserts.regionBrick));
            else if(i >= 40 && i < 50)
                this.bricks.add(new Brick(this.world, Gdx.graphics.getWidth()/10 * MathUtils.random(1,5), Gdx.graphics.getHeight()/10 * MathUtils.random(i,50) + (int)floor.getHeight(), Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/10, i, this.asserts.regionBrick));

            this.addActor(this.bricks.get(i));
        }
    }

    /*
     * 判断数组中是否有重复的值
     */
    public boolean checkIsRepeat(int[] array) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        if (hashSet.size() == array.length) {
            return false;
        } else {
            return true;
        }
    }

    //生成砖块算法,
    //生成砖块的层数
    public void generalAllBrick(int length, int bw, int bh){
        int inx = 0, jnx = 0;
        for(int i =0;i < length;i++){
            int qy = MathUtils.random(3,5);
            int[] arr = {10,11,12,13,14,15};

            if(i < 20){
                for(int j = 0; j < MathUtils.random(1,6); j++) {
                //for(int j = 0; j < 6; ) {
                    arr[j] = MathUtils.random(0, 5);//如果数组中已有指定数值就不在插入
                    //遍历数组查找重复值
                    if(!checkIsRepeat(arr)){
                        if(arr[j]%qy != 0) {
                            this.bricks.add(new Brick(this.world, bw * arr[j], Gdx.graphics.getHeight() + bh * i - bh - 100, bw, bh, i + 1 + arr[j], this.asserts.regionBrick));
                            this.addActor(this.bricks.get(inx++));
                        }else{
                            this.staticballs.add(new StaticBall(bw * arr[j], Gdx.graphics.getHeight() + bh * i - bh - 100, this.asserts));
                            this.addActor(this.staticballs.get(jnx++));
                        }
                        j++;
                    }
                }
            }else if(20 <= i && i < 50){
                for(int j = 0; j < MathUtils.random(3,6);) {
                    arr[j] = MathUtils.random(0, 5);//如果数组中已有指定数值就不在插入
                    //遍历数组查找重复值
                    if(!checkIsRepeat(arr)){
                        if(arr[j]%qy != 0) {
                            this.bricks.add(new Brick(this.world, bw * arr[j], Gdx.graphics.getHeight() + bh * i - bh - 100, bw, bh, i + 1 + arr[j], this.asserts.regionBrick));
                            this.addActor(this.bricks.get(inx++));
                        }else{
                            this.staticballs.add(new StaticBall(bw * arr[j], Gdx.graphics.getHeight() + bh * i - bh - 100, this.asserts));
                            this.addActor(this.staticballs.get(jnx++));
                        }
                        j++;
                    }
                }
            }
            else {
                for(int j = 0; j < 6;) {
                    arr[j] = MathUtils.random(0, 5);//如果数组中已有指定数值就不在插入
                    //遍历数组查找重复值
                    if(!checkIsRepeat(arr)){
                        if(arr[j]%qy != 0) {
                            this.bricks.add(new Brick(this.world, bw * arr[j], Gdx.graphics.getHeight() + bh * i - bh, bw, bh, i + 1 + arr[j], this.asserts.regionBrick));
                            this.addActor(this.bricks.get(inx++));
                        }else{
                            this.staticballs.add(new StaticBall(bw * arr[j], Gdx.graphics.getHeight() + bh * i - bh, this.asserts));
                            this.addActor(this.staticballs.get(jnx++));
                        }
                        j++;
                    }
                }
            }
        }
    }

    public void updateBrickPos(){
        for(int i = 0; i < this.bricks.size(); i++){
            Brick tBrick = this.bricks.get(i);
            Vector2 curPos = tBrick.getCurPos();
            //int curScore = tBrick.getCurSocre();
            tBrick.setCurPos((int)curPos.x, (int)(curPos.y - brickVec.y));
        }
    }

    public void updateStaticBallPos(){
        for(int i = 0; i < this.staticballs.size(); i++){
            StaticBall staticBall = this.staticballs.get(i);
            Vector2 curPos = staticBall.getCurPos();
            //int curScore = tBrick.getCurSocre();
            staticBall.setCurPos((int)curPos.x, (int)(curPos.y - brickVec.y));
        }
    }

    public boolean changeAllBall(){
        if(bomb.getState()&&bomb.getTouchState()){
            ballxy = bomb.AlreadyGet();
            bomb.setBubble(true);
            bDrawBall = true;
            bBrickCanUpdate = true;
            for (int i =0; i < balls.size(); i++){
                balls.get(i).setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);
                balls.get(i).delay(i*3);

            }
            return true;
        }
        return false;
    }

    public boolean changeBall(){
        if(bomb.getState()&&bomb.getTouchState()){
            ballxy = bomb.AlreadyGet();
            bomb.setBubble(true);
            bDrawBall = true;
            ball.setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);
            return true;
        }
        return false;
    }

    public boolean checkAllBb(){
        for(int i =0; i < this.bricks.size(); i++){
            if(checkAllBallBrick(this.bricks.get(i))){
                i--;
            }
        }
        return true;
    }

    //检测一个球与砖块的距离
    public boolean checkOneBrickClission(Brick brick){
        for(int i = 0; i < this.balls.size(); i++){
            Ball oBall = this.balls.get(i);
            Vector2 oPos = oBall.getNowPos();
            Vector2 bPos = brick.getNowPos();
            BoxClick.isClick(oPos.x, oPos.y, bPos.x, bPos.y, ballWidth, ballWidth, brickWidth, brickWidth);
        }
        return false;
    }

    //检测球与砖块的距离
    public boolean checkBrickClission(){
        for(int i =0; i < this.bricks.size(); i++){
            if(checkAllBallBrick(this.bricks.get(i))){
                i--;
            }
        }
        return true;
    }

    public boolean checkAllbbc(){
        for(int i =0; i < this.bricks.size(); i++){
            if(checkbbc(this.bricks.get(i))){
                i--;
            }
        }
        return true;
    }

    //检测静态得分球有效碰撞带来的效果
    public void checkAllSBall(){
        for(int i =0; i < this.staticballs.size(); i++){
            StaticBall sBall = this.staticballs.get(i);
            if(checkStaticBall(sBall)){
                this.getRoot().removeActor(sBall);
                this.staticballs.remove(sBall);
                bOneMoreBall = true;
                asserts.clickSound.play(1);
                i--;
                nOnes++;
            }
        }
    }

    public boolean checkStaticBall(StaticBall sball) {
        if (!bDrawBall)
            return false;
        Vector2[] arrSBallRect = sball.getRect();
        for (int bi = 0; bi < balls.size(); bi++) {
            Vector2[] arrBallRect = balls.get(bi).getRect();
            int lb = 0, rb = 0, rt = 0, lt = 0;
            for (int i = 0; i < arrBallRect.length; i++) {
                if (b2d.checkTwoBox(arrSBallRect[0], arrSBallRect[1], arrSBallRect[2], arrSBallRect[3], arrBallRect[i])) {
                    if (i == 0) lb = 1;
                    if (i == 1) rb = 1;
                    if (i == 2) rt = 1;
                    if (i == 3) lt = 1;
                }
            }
            if(lb == 1 || rb == 1 || lt == 1 || rt == 1)
                return true;
        }
        return false;
    }

    public boolean checkbbc(Brick mBrick){
        if(!bDrawBall)
            return false;

        Rectangle rc = mBrick.geBounds();
        for (int bi = 0; bi < balls.size(); bi++) {
            Rectangle rcball = balls.get(bi).geBounds();
            //if(rc.overlaps(balls.get(bi).geBounds())){
            //    balls.get(bi).setReverseHorizenDir();
            //    balls.get(bi).setReverseVerticalDir();
            //}

            Log.d(TAG, "==> x:" + rc.x + " y:" + rc.y + " width:" + rc.width + " height:" + rc.height);
            Log.d(TAG, "==> ballx:" + rcball.x + " y:" + rcball.y + " width:" + rcball.width + " height:" + rcball.height);
        }

        return true;
    }


    /*
    * 分析每个球球的移动方向
    * 1.如果球是向右上角移动的话那么球碰右边界的反弹方向应该变成左上角   -->  == <--，判断rt与rb是否碰撞
    * 2.如果球是向右下角移动的话那么球碰右边界的反弹方向应该变成左下角   -->  == <--，判断rt与rb是否碰撞
    * 3.如果球是向左上角移动的话那么球碰左边界的反弹方向应该变成右上角   <--  == -->，判断lt与lb是否碰撞
    * 4.如果球是向左下角移动的话那么球碰左边界的反弹方向应该变成右下角   <--  == -->，判断lt与lb是否碰撞
    * 5.如果球是向左下角移动的话那么球碰上边界的反弹方向应该变成左上角   下 == 上， 判断lb与rb是否碰撞
    * 6.如果球是向右下角移动的话那么球碰上边界的反弹方向应该变成右上角   下 == 上， 判断lb与rb是否碰撞
    * 7.如果球是向左上角移动的话那么球碰上边界的反弹方向应该变成左下角   上 == 下， 判断lt与rt是否碰撞
    * 8.如果球是向右上角移动的话那么球碰上边界的反弹方向应该变成右下角   上 == 下， 判断lt与rt是否碰撞
    * */

    public boolean checkAllBallBrick(Brick mBrick){
        if(!bDrawBall)
            return false;
        Vector2[] arrBrickRect = mBrick.getRect();

        for (int bi = 0; bi < balls.size(); bi++){
            Vector2[] arrBallRect = balls.get(bi).getRect();
            int lb = 0, rb = 0, rt = 0, lt = 0;
            for(int i = 0; i < arrBallRect.length; i++){
                if(b2d.checkTwoBox(arrBrickRect[0], arrBrickRect[1], arrBrickRect[2], arrBrickRect[3], arrBallRect[i])){
                    if(i == 0)lb = 1;
                    if(i == 1)rb = 1;
                    if(i == 2)rt = 1;
                    if(i == 3)lt = 1;
                }
            }

            //正面向左或者向右撞击导致的横向移动方向改变
            if((rt == 1 && rb == 1) || (lt == 1 && lb == 1)){
                balls.get(bi).setReverseHorizenDir();
            }else if((lb == 1 && rb == 1) || (lt == 1 && rt == 1)){//正面向上或者向下撞击造成的移动方向改变
                balls.get(bi).setReverseVerticalDir();
            }else if(lb == 1){
                balls.get(bi).setReverseVerticalDir();
                balls.get(bi).setReverseHorizenDir();
            }else if(lt == 1){
                //balls.get(bi).setReverseVerticalDir();
                balls.get(bi).setReverseHorizenDir();
            }else if(rb == 1){
                balls.get(bi).setReverseVerticalDir();
                balls.get(bi).setReverseHorizenDir();
            }else if(rt == 1){
                //balls.get(bi).setReverseVerticalDir();
                balls.get(bi).setReverseHorizenDir();
            }

//            //右上角的左侧撞击与顶部撞击
//            if(lt == 0 &&  lb == 0 && rt == 1 && rb == 0) {
//                balls.get(bi).setReverseHorizenDir();
//            }
//
//            if(lt == 1 &&  lb == 0 && rt == 0 && rb == 0) {
//                balls.get(bi).setReverseHorizenDir();
//            }
//
//            if(lt == 0 &&  lb == 1 && rt == 0 && rb == 0) {
//                balls.get(bi).setReverseVerticalDir();
//            }
//
//            if(lt == 0 &&  lb == 0 && rt == 0 && rb == 1) {
//                balls.get(bi).setReverseVerticalDir();
//            }

            /*
            if(lb == 1 && rb == 1){
                balls.get(bi).setTopBottomDirection(true);
            }

            if((lb == 1 && lt == 1)||lb == 1 || lt == 1){
                balls.get(bi).setLeftRightDirection(false);
            }

            if((rb == 1 && rt == 1) || rb == 1 || rt == 1){
                balls.get(bi).setLeftRightDirection(true);
            }

            if((rt == 1 && lt == 1)|| (rt == 1 && lt == 0) || (rb == 0 && rt == 0 && lt == 1) || rt == 1 || lt == 1){
                balls.get(bi).setTopBottomDirection(false);
            }
            */
            if(rt == 1 || lt == 1 || lb == 1 || rb == 1) {
                //Log.d(TAG, "==>>rt:" + rt + " lt:" + lt + " lb:" + lb + " rb:" + rb);
                asserts.bitSound.play(1);
                if(mBrick.setNum(0)){
                    this.getRoot().removeActor(mBrick);
                    this.bricks.remove(mBrick);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkAllBallBrick(){
        if(!bDrawBall)
            return false;
        Vector2[] arrBrickRect = brick.getRect();

        for (int bi = 0; bi < balls.size(); bi++){
            Vector2[] arrBallRect = balls.get(bi).getRect();
            int lb = 0, rb = 0, rt = 0, lt = 0;
            for(int i = 0; i < arrBallRect.length; i++){
                if(b2d.checkTwoBox(arrBrickRect[0], arrBrickRect[1], arrBrickRect[2], arrBrickRect[3], arrBallRect[i])){
                    if(i == 0)lb = 1;
                    if(i == 1)rb = 1;
                    if(i == 2)rt = 1;
                    if(i == 3)lt = 1;
                }
            }

            if(lb == 1 && rb == 1){
                balls.get(bi).setTopBottomDirection(true);
            }

            if((lb == 1 && lt == 1)||lb == 1 || lt == 1){
                balls.get(bi).setLeftRightDirection(false);
            }

            if((rb == 1 && rt == 1) || rb == 1 || rt == 1){
                balls.get(bi).setLeftRightDirection(true);
            }

            if((rt == 1 && lt == 1)|| (rt == 1 && lt == 0) || (rt == 0 && lt == 1) || rt == 1 || lt == 1){
                balls.get(bi).setTopBottomDirection(false);
            }

            if(rt == 1 || lt == 1 || lb == 1 || rb == 1) {
                //Log.d(TAG, "==>>rt:" + rt + " lt:" + lt + " lb:" + lb + " rb:" + rb);
                asserts.bitSound.play(1);
                brick.setNum(0);
            }
        }

        return true;
    }

    //检测是否可以向下移动砖块
    public void checkCanTouch(){
        for(int i = 0; i < balls.size(); i++){
            if(balls.get(i).isrun())
                continue;
            if(i == balls.size() - 1){
                bomb.setBubble(false);
                if(bBrickCanUpdate) {
                    updateBrickPos();

                    if(bOneMoreBall){
                        for(int num = 0; num < nOnes; num++){
                            generareOneBall(this.world);
                            bOneMoreBall = false;
                        }
                        nOnes = 0;
                    }

                    updateStaticBallPos();
                }
                bBrickCanUpdate = false;
            }
        }
    }

    public boolean checkGameOver(){
        for(int i = 0; i < bricks.size(); i++){
            Brick brick = bricks.get(i);
            Vector2[] vec = brick.getRect();
            //Log.d(TAG, "==>> y:" + vec[0].y + " floor y:" + (int)floor.getHeight());
            //if(vec[0].y <= (int)floor.getHeight() + Gdx.graphics.getHeight()/2) {
            if(vec[0].y <= (int)floor.getHeight() + 50) {
                bomb.setBubble(true);
                return true;
            }
        }
        return false;
    }

    public boolean checkBallBrick(){
        if(!bDrawBall)
            return false;
        Vector2[] arrBallRect = ball.getRect();
        Vector2[] arrBrickRect = brick.getRect();

        int lb = 0, rb = 0, rt = 0, lt = 0;
        for(int i = 0; i < arrBallRect.length; i++){
            if(b2d.checkTwoBox(arrBrickRect[0], arrBrickRect[1], arrBrickRect[2], arrBrickRect[3], arrBallRect[i])){
                if(i == 0)lb = 1;
                if(i == 1)rb = 1;
                if(i == 2)rt = 1;
                if(i == 3)lt = 1;
            }
        }

        if(lb == 1 && rb == 1){
            ball.setTopBottomDirection(true);
        }

        if((lb == 1 && lt == 1)||lb == 1 || lt == 1){
            ball.setLeftRightDirection(false);
        }

        if((rb == 1 && rt == 1) || rb == 1 || rt == 1){
            ball.setLeftRightDirection(true);
        }

        if((rt == 1 && lt == 1)|| (rt == 1 && lt == 0) || (rt == 0 && lt == 1) || rt == 1 || lt == 1){
            ball.setTopBottomDirection(false);
        }

        if(rt == 1 || lt == 1 || lb == 1 || rb == 1) {
            //Log.d(TAG, "==>>rt:" + rt + " lt:" + lt + " lb:" + lb + " rb:" + rb);
            brick.setNum(0);
        }

        return true;
    }

    public void update(){
        //batch.draw(asserts.regionBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //if(bDrawBall)
        //    ball.setvum((int)ballxy[0], (int)ballxy[1], ballxy[2]);

        /*
        changeBall();
        checkBallBrick();
        if(!ball.isrun())
            bomb.setBubble(false);
        */
        /*
        changeAllBall();
        checkAllBallBrick();
        for(int i = 0; i < balls.size(); i++){
            if(balls.get(i).isrun())
                continue;
            if(i == balls.size() - 1)
                bomb.setBubble(false);
        }
        */

        //实现逻辑每点击攻击一次，所有蛋蛋向下拖动一个砖块的高度
        changeAllBall(); //初始化所有导弹
        //checkAllBb();    //检测导弹与砖块的碰撞
        //checkAllbbc();
        checkAllSBall();
        checkCanTouch(); //检测是否可以点击
        bomb.setBubble(balls.size());
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }
}
