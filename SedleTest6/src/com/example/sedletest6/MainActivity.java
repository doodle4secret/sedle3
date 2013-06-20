package com.example.sedletest6;

import com.example.sedletest6.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wire up the add button to add a new photo
        Button add = (Button) findViewById(R.id.button1);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
            	LinearLayout ll = (LinearLayout)sv.getChildAt(0);
            	
            	Doodle doodle;
            	int sz = ll.getChildCount();
            	if (sz > 0) {
            		Doodle up = (Doodle)ll.getChildAt(sz - 1);
            		doodle = new Doodle(v.getContext(), up.getBgcolor());
            	} else {
            		doodle = new Doodle(v.getContext(), 0x00000000);
            	}
            	ll.addView(doodle);
            }
        });
        
        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
            	LinearLayout ll = (LinearLayout)sv.getChildAt(0);
            	
            	ll.removeAllViews();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
