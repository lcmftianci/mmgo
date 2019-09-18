package com.example.myapplication.GeniusDirection;

import com.badlogic.gdx.math.Vector2;

/**
  * 构建贝塞尔曲线类
  * @author whs
  *
  */
public abstract class Bezier {
 
	protected Vector2 tmpVec;
 
	public abstract Vector2 apply(float f);
 
	public Bezier() {
		tmpVec = new Vector2();
	}
 
	public static Bezier cubic(float x1, float y1, float x2, float y2, float x3,
			float y3, float x4, float y4) {
		return cubic(new Vector2(x1, y1), new Vector2(x2, y2), new Vector2(x3,
				y3), new Vector2(x4, y4));
	}
 
	public static Bezier cubic(Vector2 p0, Vector2 p1,
			Vector2 p2, Vector2 p3) {
		return new CubicBezier(p0, p1, p2, p3);
	}
 
	public static Bezier linear(float x1, float y1, float x2, float y2) {
		return linear(new Vector2(x2, y1), new Vector2(x2, y2));
	}
 
	public static Bezier linear(Vector2 p0, Vector2 p1) {
		return new LinearBezier(p0, p1);
	}
 
	public static Bezier quadratic(float x1, float y1, float x2, float y2, float x3,
			float y3) {
		return quadratic(new Vector2(x1, y1), new Vector2(x2, y2), new Vector2(x3,
				y3));
	}
 
	public static Bezier quadratic(Vector2 p0, Vector2 p1,
			Vector2 p2) {
		return new QuadraticBezier(p0, p1, p2);
	}
 
	/**
	 * 三次方贝塞尔曲线
	 * @author whs
	 *
	 */
	public static class CubicBezier extends Bezier {
 
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
	public static class LinearBezier extends Bezier {
 
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
	public static class QuadraticBezier extends Bezier {
 
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
}
 
 