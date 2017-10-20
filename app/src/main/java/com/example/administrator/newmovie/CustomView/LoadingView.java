package com.example.administrator.newmovie.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.administrator.newmovie.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class LoadingView extends SurfaceView implements SurfaceHolder.Callback {
    int[] loadings = {
            R.drawable.loading00, R.drawable.loading01, R.drawable.loading02, R.drawable.loading03, R.drawable.loading04,
            R.drawable.loading05, R.drawable.loading06, R.drawable.loading07, R.drawable.loading08, R.drawable.loading09,
            R.drawable.loading10, R.drawable.loading11, R.drawable.loading12, R.drawable.loading13, R.drawable.loading14,
            R.drawable.loading15, R.drawable.loading16, R.drawable.loading17, R.drawable.loading18, R.drawable.loading19,
            R.drawable.loading20, R.drawable.loading21, R.drawable.loading22, R.drawable.loading23, R.drawable.loading24,
            R.drawable.loading25, R.drawable.loading26, R.drawable.loading27, R.drawable.loading28, R.drawable.loading29,
    };
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), loadings[0]);
    private SurfaceHolder holder;
    private MyThread myThread;
    int width ;
    int height ;
     int x ;
    int y ;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        x = (width - bitmap.getWidth()) / 2;
        y = (height - bitmap.getHeight()) / 2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        holder = this.getHolder();
        holder.addCallback(this);
//        myThread = new MyThread(holder);//创建一个绘图线程
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread = new MyThread(holder);
        myThread.isRun = true ;
        myThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myThread.isRun = false ;
    }

    class MyThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRun;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count = 0;
            while (isRun) {
                Canvas c = null;
                try {
                    synchronized (holder) {
                        c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        c.drawColor(Color.WHITE);
                        Paint p = new Paint(); //创建画笔
                        p.setColor(Color.WHITE);
                        count++;
                        if (count >= loadings.length)
                            count = 0;
                        bitmap = BitmapFactory.decodeResource(getResources(), loadings[count]);
                        c.drawBitmap(bitmap, x, y, p);
                        Thread.sleep(20);//睡眠时间为1秒
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。

                    }
                }
            }
        }
    }
}
