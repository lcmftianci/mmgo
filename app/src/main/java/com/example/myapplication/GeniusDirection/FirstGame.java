package com.example.myapplication.GeniusDirection;
import android.util.Log;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.microedition.khronos.opengles.GL10;

class FirstGame implements ApplicationListener {
  private static String UP = "up";
  private static String DOWN = "down";
  private static String LEFT = "left";
  private static String RIGHT = "right";
  //舞台
  private Stage stage;
  //演员
  private Actor firstActor;
  private Texture texture;
  private Button buttonUp,buttonDown,buttonLeft,buttonRight;
  private NinePatch patch1, patch2;
  @Override
  public void create() {
//    stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
//    firstActor = new FirstActor("renwu");
    buttonUp = initButton(UP,40,80);
    buttonDown = initButton(DOWN,40,0);
    buttonLeft = initButton(LEFT,0,40);
    buttonRight = initButton(RIGHT,80,40);
//    buttonUp.setClickListener(this);
//    buttonDown.setClickListener(this);
//    buttonLeft.setClickListener(this);
//    buttonRight.setClickListener(this);
    stage.addActor(firstActor);
    stage.addActor(buttonUp);
    stage.addActor(buttonDown);
    stage.addActor(buttonLeft);
    stage.addActor(buttonRight);
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render() {
    // 清屏
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

    @Override
  public void dispose() {
    // 释放占用的资源
    stage.dispose();
  }
  @Override
  public void resume() {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resize(int width, int height) {

  }

  public Button initButton(String name,int x,int y){
    if(name.equals(UP)){
      texture = new Texture(Gdx.files.internal("up_alt.png"));
    }else if(name.equals(DOWN)){
      texture = new Texture(Gdx.files.internal("down_alt.png"));
    }else if(name.equals(LEFT)){
      texture = new Texture(Gdx.files.internal("back_alt.png"));
    }else if(name.equals(RIGHT)){
      texture = new Texture(Gdx.files.internal("forward_alt.png"));
    }
    patch1 = new NinePatch(texture, 0, 0, 0, 0);
    //Button button = new Button(new ButtonStyle(patch1, patch1, patch1, 0f, 0f, 0f, 0f, null, null), name);
    Button button = new Button(new ButtonStyle());
//    button.x = x;
//    button.y = y;
//    button.width = 32;
//    button.height = 32;
    return button;
  }

//  @Override
//  public void click(Actor button) {
//    if(button.equals(buttonUp)){
//      Actor actor = button.parent.findActor("renwu");;
//      actor.y += 10;
//      Log.i("touch", "up");
//    }else if(button.equals(buttonDown)){
//      Actor actor = button.parent.findActor("renwu");;
//      actor.y -= 10;
//      Log.i("touch", "down");
//    }else if(button.equals(buttonLeft)){
//      Actor actor = button.parent.findActor("renwu");;
//      actor.x -= 10;
//      Log.i("touch", "left");
//    }else if(button.equals(buttonRight)){
//      Actor actor = button.parent.findActor("renwu");;
//      actor.x += 10;
//      Log.i("touch", "right");
//    }
//  }
}
