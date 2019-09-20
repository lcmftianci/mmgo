package com.example.myapplication.BingGo;

public class BgWorld{

    private final String TAG = "BgWorld";
    BgSpirit ball;
    //CHBezierLine cbl;

    public interface BgWorldListener {
        public void jump();

        public void highJump();

        public void hit();

        public void coin();
    }

    public BgWorld (BgWorldListener listener) {
        this.ball = new BgSpirit(5, 1);
        // 起点，控制点，控制点，终点
//        Bezier<Vector2> bezier = new Bezier<Vector2>(
//                new Vector2(10, 300),
//                new Vector2(100, 500),
//                new Vector2(300, 100),
//                new Vector2(410, 300));
        //this.cbl = new CHBezierLine(bezier);
//        this.platforms = new ArrayList<Platform>();
//        this.springs = new ArrayList<Spring>();
//        this.squirrels = new ArrayList<Squirrel>();
//        this.coins = new ArrayList<Coin>();
//        this.listener = listener;
//        rand = new Random();
//        generateLevel();
//
//        this.heightSoFar = 0;
//        this.score = 0;
//        this.state = WORLD_STATE_RUNNING;
    }

    public void update(int x, int y) {
        ball.update(x, y);
//        Log.d(TAG, "x:" + x + " y:" + y );
        //ball.position.add(x, y);
    }
}
