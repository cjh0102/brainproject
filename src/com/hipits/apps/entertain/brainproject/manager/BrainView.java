package com.hipits.apps.entertain.brainproject.manager;

import java.util.ArrayList;
import java.util.List;

import kr.imus.brainproject.model.BrainViewModel;
import kr.imus.brainproject.model.Point;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BrainView extends View{
	private BrainViewModel brainViewModel;
	private String name;
	
	public BrainView(Context context, BrainViewModel brainViewModel, String name) {
		super(context);
		this.brainViewModel = brainViewModel;
		this.name = name;
	}

	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
		drawBrainView(canvas);
	}
	
	public void drawBrainView(Canvas canvas) {
		Paint paint = new Paint();
		BrainManager brainManager = BrainManager.getInstance();

		canvas.drawARGB(255, 255, 255, 255);
		canvas.drawBitmap(brainViewModel.getBitmap(), null, new Rect (0, 0, 0 + brainManager.getCurWidth(),
				brainManager.getCurHeight()), paint);
		
		// title 
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(brainManager.calX(9f));
		canvas.drawText(name + "님의 뇌구조", brainManager.calX(50), brainManager.calY(10), paint);
		
		List<Integer> colors = brainViewModel.getColors();
		List<Point> points = brainViewModel.getPoints();
		List<Float> fonts = fixFontSize(brainViewModel.getFonts(), brainViewModel.getItems());

		for (int i = 0; i < brainViewModel.getNum(); i++) {
			
			paint.setTextAlign(Align.CENTER);
			paint.setTextSize(brainManager.calX(fonts.get(i)));
			paint.setColor(colors.get(i));
			
			canvas.drawText(brainViewModel.getItems().get(i), 
					brainManager.calX(points.get(i).getX()),
					brainManager.calY(points.get(i).getY()), paint);
		}
	}
	
	public List<Float> fixFontSize(List<Float> fontSizes, List<String> items) {
		List<Float> retSizes = new ArrayList<Float>();
		
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).length() <= 2) {
				float fixSize = fontSizes.get(i) + 1.7f;
				Log.d("fixSize:", "" + fixSize);
				retSizes.add(fixSize);
			} else if (items.get(i).length() == 3) {
				float fixSize = fontSizes.get(i) + 0.5f;
				Log.d("fixSize:", "" + fixSize);
				retSizes.add(fixSize);
			} 
			else {
				retSizes.add(fontSizes.get(i));
				Log.d("notFix", ""+ fontSizes.get(i));
			}
		}
		return retSizes;
	}
}
