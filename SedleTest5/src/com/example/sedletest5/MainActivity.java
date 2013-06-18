package com.example.sedletest5;

import android.os.Bundle;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AbsListView;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    PhotoAdapter mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        // Set up our adapter
        mAdapter = new PhotoAdapter(this);
        setListAdapter(mAdapter);
        
        // Wire up the clear button to remove all photos
        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mAdapter.clearPhotos();
            } });
        
        // Wire up the add button to add a new photo
        Button add = (Button) findViewById(R.id.button1);
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mAdapter.addPhotos();
            } });    
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
  */ 

    
    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids. 
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class PhotoAdapter extends BaseAdapter {

        private Integer[] mPhotoPool = {
                R.drawable.test800, R.drawable.test400, R.drawable.test1, R.drawable.test2};
        
        private ArrayList<Integer> mPhotos = new ArrayList<Integer>();
        private ArrayList<Integer> mColors = new ArrayList<Integer>();
        private ArrayList<Integer> mHeights = new ArrayList<Integer>();
        
        public PhotoAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mPhotos.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	/*
            // Make an ImageView to show a photo
            ImageView i = new ImageView(mContext);

            i.setImageResource(mPhotos.get(position));
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            // Give it a nice background
            i.setBackgroundResource(R.drawable.ic_launcher);
            */
        	
        	DrawView i = new DrawView(mContext);
        	
        	if (mColors.get(position) == 0) {
        		int bgcolor = (int)Math.round(Math.random() * 0xffffff);
        		mColors.set(position, bgcolor);
        	}
        	i.setBackgroundColor(0xff000000 | mColors.get(position));
        	i.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, mHeights.get(position)));
        	
//        	System.out.println(position + " : " + bgcolor);
        	if (position > 0) {
				i.paint.setColor(0xff000000 | mColors.get(position - 1));

        		/*
        		View up = parent.getChildAt(position - 1);
        		if (up != null) {
                	System.out.println("up exist");
        			Drawable d = up.getBackground();
        			if (d instanceof ColorDrawable) {
                    	System.out.println("color is " + (((ColorDrawable)d).getColor() | 0x00ffffff));
        				i.paint.setColor(((ColorDrawable)d).getColor());
        			}
        		}
        		*/
/*        		View aline = new View(mContext);
        		aline.setBackground(parent.getChildAt(position - 1).getBackground());
        		aline.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 1));
        		*/
//        		i.
//        		i.setBackground(parent.getChildAt(position - 1).getBackground());
//        		Drawable d = ;
  //      		System.out.println(d.toString());
  //      		View up = (View)getItem(0);
    //    		up.getBackground();
        	}
            return i;
        }

        private Context mContext;

        public void clearPhotos() {
            mPhotos.clear();
            mHeights.clear();
            mColors.clear();
            notifyDataSetChanged();
        }
        
        public void addPhotos() {
            int whichPhoto = (int)Math.round(Math.random() * (mPhotoPool.length - 1));
            int newPhoto = mPhotoPool[whichPhoto];
            mColors.add(0);
            mHeights.add(100 + (int)Math.round(Math.random() * 100));
            mPhotos.add(newPhoto);
            notifyDataSetChanged();
        }

        class DrawView extends View {
            Paint paint = new Paint();

            
            public DrawView(Context context) {
                super(context);
                paint.setColor(0x00000000);
            }
            @Override
            public void onDraw(Canvas canvas) {
               super.onDraw(canvas);
//               canvas.drawLine(0, 0, this.getWidth(), 0, paint);
               canvas.drawLine(0, 2, this.getWidth(), 2, paint);
               canvas.drawLine(0, 3, this.getWidth(), 3, paint);

            }
    }        
    }
}
