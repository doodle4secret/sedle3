package com.example.sedletest6;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Doodle extends RelativeLayout {
    private Integer[] mStringPool = {
            R.string.post0, R.string.post1, R.string.post2, R.string.post3, R.string.post4, R.string.post5, R.string.post6,
            R.string.post7, R.string.post8, R.string.post9, R.string.post10, R.string.post11 };
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
		tv.setPadding(24, 24, 24, 24);
		if (r * 0.30 + g * 0.59 + b * 0.11 < 128) {
			tv.setTextColor(0xffffffff);
		} else {
			tv.setTextColor(0xff000000);
		}		
		this.addView(tv);
		
		LineView lv = new LineView(context);
		lv.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 3));
		lv.paint.setColor(upcolor);
		lv.paint2.setColor(0x7fffffff & upcolor);
		this.addView(lv);
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
