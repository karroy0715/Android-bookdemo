package com.example.interpolators_test265;

import java.util.ArrayList;

import android.R.anim;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements 
       AdapterView.OnItemSelectedListener {
	private static final String[] INTERPOLATORS={"Accelerate","Decelerate",
		"Accelerate/Decelerate","Anticipate","Overshoot",
		"Anticipate/Overshoot","Bounce"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
    Spinner s=(Spinner)findViewById(R.id.spinner1);
	ArrayAdapter<String> adapter=new ArrayAdapter(this, 
    		android.R.layout.simple_spinner_item,INTERPOLATORS);
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    s.setAdapter(adapter);
    s.setOnItemSelectedListener(this);
    }
	@Override
	public void onItemSelected(AdapterView parent, View v1, int position,
			long id) {
		// TODO Auto-generated method stub
		final View target=findViewById(R.id.target);
		final View targetParent=(View)target.getParent();
		Animation animation=new TranslateAnimation(0.0f,targetParent.getWidth()
				-target.getWidth()-targetParent.getPaddingLeft()
				-targetParent.getPaddingRight(),0.0f,0.0f);
		animation.setDuration(1000);
		animation.setStartOffset(300);
		animation.setRepeatMode(Animation.RESTART);
		animation.setRepeatCount(animation.INFINITE);
		switch (position) {
		case 0:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.accelerate_interpolator));
			break;
		case 1:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.decelerate_interpolator));
			break;
		case 2:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.accelerate_decelerate_interpolator));
			break;
		case 3:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.anticipate_interpolator));
			break;
		case 4:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.overshoot_interpolator));
			break;
		case 5:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.anticipate_overshoot_interpolator));
			break;
		case 6:
			animation.setInterpolator(AnimationUtils.loadInterpolator(this,
					android.R.anim.bounce_interpolator));
			break;
		
		}
		target.startAnimation(animation);
	}

	@Override
	public void onNothingSelected(AdapterView parent) {
		// TODO Auto-generated method stub
		
	}
    
  
    
}
