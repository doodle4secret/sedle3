package com.example.sedletest6;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Doodle extends RelativeLayout {
    private Integer[] mStringPool = {
            R.string.post0, R.string.post1, R.string.post2, R.string.post3, R.string.post4, R.string.post5, R.string.post6,
            R.string.post7, R.string.post8, R.string.post9, R.string.post10, R.string.post11 };
    private Integer[] mImagePool = {
            R.drawable.person, R.drawable.person2,
            R.drawable.thumb, R.drawable.thumb2,};
	private int bgcolor;
	
	public int getBgcolor() { return bgcolor; }
	
	public Doodle(Context context, int upcolor) {
		super(context);
//		rl = new RelativeLayout(context);
		this.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		int r = (int)Math.round(Math.random() * 0xff);
		int g = (int)Math.round(Math.random() * 0xff);
		int b = (int)Math.round(Math.random() * 0xff);
		bgcolor = 0xff000000 | (r<<16) | (g<<8) | b;
		this.setBackgroundColor(bgcolor);
		
		TextView tv = new TextView(context);
		tv.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		int resid = (int)Math.round(Math.random() * (mStringPool.length - 1));
		tv.setText(mStringPool[resid]);
		
		float den = context.getResources().getDisplayMetrics().density;
		int dp20 = (int)(20 * den);
		tv.setPadding(dp20, dp20, dp20, dp20);
		boolean bg_is_dark = r * 0.30 + g * 0.59 + b * 0.11 < 128; 
		tv.setTextColor(bg_is_dark ? 0xffffffff : 0xff000000);
		this.addView(tv);
		
		LineView lv = new LineView(context);
		lv.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 3));
		lv.paint.setColor(upcolor);
		lv.paint2.setColor(0x7fffffff & upcolor);
		this.addView(lv);
		
		ImageView iv = new ImageView(context);
        iv.setImageResource(bg_is_dark ? mImagePool[3] : mImagePool[2]);
        iv.setAdjustViewBounds(true);
        int dp18 = (int)(18 * den);
        int dp16 = (int)(16 * den);
        int dp2 = (int)(2 * den);
        int dp4 = (int)(4 * den);
        RelativeLayout.LayoutParams ivlp = new RelativeLayout.LayoutParams(dp18, dp16);
        ivlp.setMargins(dp2, dp20 + dp4, dp2, dp4);
        iv.setLayoutParams(ivlp);
        iv.setAlpha(0.4f);
//        iv.setBackgroundResource(R.drawable.ic_launcher);		
		this.addView(iv);
	}
	
    class LineView extends View {
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        
        public LineView(Context context) {
            super(context);
            paint.setColor(0x00000000);
            paint2.setColor(0x00000000);
        }
        @Override
        public void onDraw(Canvas canvas) {
           super.onDraw(canvas);
//           canvas.drawLine(0, 0, this.getWidth(), 0, paint);
           canvas.drawLine(0, 1, this.getWidth(), 1, paint2);
           canvas.drawLine(0, 2, this.getWidth(), 2, paint);

        }
    }
}
