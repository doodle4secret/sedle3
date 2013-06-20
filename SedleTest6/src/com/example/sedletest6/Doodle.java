package com.example.sedletest6;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	
	private int DP(int pixel) { return (int)(getContext().getResources().getDisplayMetrics().density * pixel); }
	
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
		
		tv.setPadding(DP(20), DP(20), DP(20), DP(20));
		boolean bg_is_dark = r * 0.30 + g * 0.59 + b * 0.11 < 128; 
		tv.setTextColor(bg_is_dark ? 0xffffffff : 0xff000000);
		this.addView(tv);
		
		LineView lv = new LineView(context);
		lv.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 3));
		lv.paint.setColor(upcolor);
		lv.paint2.setColor(0x7fffffff & upcolor);
		this.addView(lv);

		for (int i = 0; i < 4; i++) {
			ImageView iv = new ImageView(context);
			iv.setImageResource(bg_is_dark ? mImagePool[1] : mImagePool[0]);
			iv.setAdjustViewBounds(true);

			RelativeLayout.LayoutParams ivlp = new RelativeLayout.LayoutParams(DP(22), DP(20));
			ivlp.setMargins(DP(22 * i), DP(2), DP(0), DP(0));
			iv.setLayoutParams(ivlp);
			iv.setPadding(DP(2), DP(2), DP(2), DP(2));
				
			iv.setAlpha(0.4f);
			this.addView(iv);
		}
		
		for (int i = 0; i < 4; i++) {
			ImageView iv = new ImageView(context);
			iv.setImageResource(bg_is_dark ? mImagePool[3] : mImagePool[2]);
			iv.setAdjustViewBounds(true);

			RelativeLayout.LayoutParams ivlp = new RelativeLayout.LayoutParams(DP(22), DP(20));
			ivlp.setMargins(DP(0), DP(2) + DP(22 * (i + 1)), DP(0), DP(0));
			iv.setLayoutParams(ivlp);
			iv.setPadding(DP(2), DP(2), DP(2), DP(2));
				
			iv.setAlpha(0.4f);
			this.addView(iv);
		}
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
