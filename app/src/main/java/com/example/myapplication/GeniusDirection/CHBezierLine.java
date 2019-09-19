package com.example.myapplication.GeniusDirection;
 
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
//import com.oahcfly.chgame.core.mvc.CHActor;
 
/**
 * 
 * 贝塞尔路线 : 使用ShapeRenderer绘制
 * @author haocao
 *
 */
public class CHBezierLine extends CHActor {
 
	// 以左上角为原点进行绘制的
	private ShapeRenderer shapeRenderer;
	private Bezier<Vector2> bezier;
	// 点列表
	private Vector2[] points;
	// 线条颜色
	private Color lineColor = Color.RED;
 
	public CHBezierLine (Bezier<Vector2> bezier) {
		this(bezier, Color.RED);
	}
 
	public CHBezierLine (Bezier<Vector2> bezier, Color lineColor) {
		updateBezier(bezier);
		this.lineColor = lineColor;
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
	}
 
	/**
	 * 更新贝塞尔曲线参数
	 * @param bezier
	 */
	public void updateBezier (Bezier<Vector2> bezier) {
		this.bezier = bezier;
		// 计算所有点
		int dis = (int)(this.bezier.points.get(this.bezier.points.size - 1).x - this.bezier.points.get(0).x);
		if (dis == 0) {
			dis = (int)(this.bezier.points.get(this.bezier.points.size - 1).y - this.bezier.points.get(0).y);
		}
		points = new Vector2[dis];
		for (int i = 0; i < dis; i++) {
			float t = i * 1f / dis;
			Vector2 out = new Vector2();
			this.bezier.valueAt(out, t);
			points[i] = out;
		}
	}
 
	@Override
	public void draw (Batch batch, float parentAlpha) {
		batch.flush();
 
		// 设置用于渲染的投影矩阵
		getStage().getCamera().update();
		shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
 
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(this.lineColor);
		for (int i = 0, l = points.length - 1; i < l; i++) {
			Vector2 cur = points[i];
			Vector2 next = points[i + 1];
			shapeRenderer.line(cur, next);
		}
		shapeRenderer.end();
 
		// 结束本次绘制
		batch.end();
		// 重新开始新的
		batch.begin();
 
	}
 
	@Override
	public boolean remove () {
		// TODO Auto-generated method stub
		shapeRenderer.dispose();
		return super.remove();
	}
 
}