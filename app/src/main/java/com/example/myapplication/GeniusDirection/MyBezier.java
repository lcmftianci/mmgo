package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import javax.microedition.khronos.opengles.GL10;

/**
  * 构建贝塞尔曲线类
  * @author whs
  *
  */
public abstract class MyBezier {
 
	protected Vector2 tmpVec;
 
	public abstract Vector2 apply(float f);
 
	public MyBezier() {
		tmpVec = new Vector2();
	}
 
	public static MyBezier cubic(float x1, float y1, float x2, float y2, float x3,
			float y3, float x4, float y4) {
		return cubic(new Vector2(x1, y1), new Vector2(x2, y2), new Vector2(x3,
				y3), new Vector2(x4, y4));
	}
 
	public static MyBezier cubic(Vector2 p0, Vector2 p1,
			Vector2 p2, Vector2 p3) {
		return new CubicBezier(p0, p1, p2, p3);
	}
 
	public static MyBezier linear(float x1, float y1, float x2, float y2) {
		return linear(new Vector2(x2, y1), new Vector2(x2, y2));
	}
 
	public static MyBezier linear(Vector2 p0, Vector2 p1) {
		return new LinearBezier(p0, p1);
	}
 
	public static MyBezier quadratic(float x1, float y1, float x2, float y2, float x3,
			float y3) {
		return quadratic(new Vector2(x1, y1), new Vector2(x2, y2), new Vector2(x3,
				y3));
	}
 
	public static MyBezier quadratic(Vector2 p0, Vector2 p1,
			Vector2 p2) {
		return new QuadraticBezier(p0, p1, p2);
	}
 
	/**
	 * 三次方贝塞尔曲线
	 * @author whs
	 *
	 */
	public static class CubicBezier extends MyBezier {
 
		public Vector2 p0;
		public Vector2 p1;
		public Vector2 p2;
		public Vector2 p3;
 
		public CubicBezier(Vector2 p0, Vector2 p1,
				Vector2 p2, Vector2 p3) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		}
 
		public Vector2 apply(float f) {
			float f1 = 1.0F - f;
			float f2 = f * f;
			float f3 = f1 * f1;
			float f4 = f3 * f1;
			float f5 = f * (3F * f3);
			float f6 = f2 * (3F * f1);
			float f7 = f2 * f;
			tmpVec.x = f4 * p0.x + f5 * p1.x + f6 * p2.x + f7 * p3.x;
			tmpVec.y = f4 * p0.y + f5 * p1.y + f6 * p2.y + f7 * p3.y;
			return tmpVec;
		}
 
	}
 
	/**
	 * 线性贝塞尔曲线
	 * @author whs
	 *
	 */
	public static class LinearBezier extends MyBezier {
 
		public Vector2 p0;
		public Vector2 p1;
 
	public LinearBezier(Vector2 p0, Vector2 p1) {
	    this.p0 = p0;
	    this.p1 = p1;
	}
 
		public Vector2 apply(float f) {
			float f1 = 1.0F - f;
			tmpVec.x = f1 * p0.x + f * p1.x;
			tmpVec.y = f1 * p0.y + f * p1.y;
			return tmpVec;
		}
 
	}
 
	/**
	 * 二次贝塞尔去选
	 * @author whs
	 *
	 */
	public static class QuadraticBezier extends MyBezier {
 
		public Vector2 p0;
		public Vector2 p1;
		public Vector2 p2;
 
		public QuadraticBezier(Vector2 p0, Vector2 p1,
				Vector2 p2) {
			this.p0 = p0;
			this.p1 = p1;
			this.p2 = p2;
		}
 
		public Vector2 apply(float f) {
			float f1 = 1.0F - f;
			float f2 = f1 * f1;
			float f3 = f * (2.0F * f1);
			float f4 = f * f;
			tmpVec.x = f2 * p0.x + f3 * p1.x + f4 * p2.x;
			tmpVec.y = f2 * p0.y + f3 * p1.y + f4 * p2.y;
			return tmpVec;
		}
	}

    public static class Box2dUser implements ApplicationListener {

        protected OrthographicCamera camera;
        protected Box2DDebugRenderer renderer; // 测试用绘制器
        private World world;
        @Override
        public void create() {
            camera = new OrthographicCamera(48, 32);
            camera.position.set(0, 15, 0);
            renderer = new Box2DDebugRenderer();

            world = new World(new Vector2(0, -9.8f), true); // 一般标准重力场
            BodyDef bd = new BodyDef(); //声明物体定义
            bd.position.set(2f, 2f);
            bd.type= BodyDef.BodyType.DynamicBody;
            Body b = world.createBody(bd); //通过world创建一个物体
            CircleShape c = new CircleShape(); //创建一个形状（圆）
            c.setRadius(1f); //设置半径
            b.createFixture(c, 1f); //将形状和密度赋给物体
        }
        @Override
        public void dispose() {

            renderer.dispose();
            world.dispose();
            renderer = null;
            world = null;
        }
        @Override
        public void pause() {
            // TODO Auto-generated method stub
        }
        @Override
        public void render() {
            world.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);
            GL20 gl = Gdx.app.getGraphics().getGL20();
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            camera.update();
            renderer.render(world, camera.combined);
        }
        @Override
        public void resize(int width, int height) {
            // TODO Auto-generated method stub
        }
        @Override
        public void resume() {
            // TODO Auto-generated method stub
        }
    }
}
 
 