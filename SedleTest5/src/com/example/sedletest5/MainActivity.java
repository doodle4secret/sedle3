package com.example.sedletest5;

import android.os.Bundle;

import android.app.ListActivity;
import android.content.Context;
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
                R.drawable.test1, R.drawable.test2};

        private ArrayList<Integer> mPhotos = new ArrayList<Integer>();
        
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
            // Make an ImageView to show a photo
            ImageView i = new ImageView(mContext);

            i.setImageResource(mPhotos.get(position));
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            // Give it a nice background
            i.setBackgroundResource(R.drawable.ic_launcher);
            return i;
        }

        private Context mContext;

        public void clearPhotos() {
            mPhotos.clear();
            notifyDataSetChanged();
        }
        
        public void addPhotos() {
            int whichPhoto = (int)Math.round(Math.random() * (mPhotoPool.length - 1));
            int newPhoto = mPhotoPool[whichPhoto];
            mPhotos.add(newPhoto);
            notifyDataSetChanged();
        }

    }
}
