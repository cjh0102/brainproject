package com.hipits.apps.entertain.brainproject.manager;

import kr.imus.brainproject.activity.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapManager {
	private static final int[] bitmapId = { R.drawable.brain01, R.drawable.brain02,
		R.drawable.brain03, R.drawable.brain04, R.drawable.brain05,
		R.drawable.brain06, R.drawable.brain07, R.drawable.brain08,};
	
	private static BitmapManager bitmapManager;
	
	private BitmapManager (){}
	
	public static BitmapManager getInstance(){
		if (bitmapManager == null){
			bitmapManager = new BitmapManager ();
		}
		return bitmapManager;
	}
	
	public Bitmap getBitmapForId(Context context, int num) {
		
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId[num-1]);
		return bitmap;
	}
	
}
