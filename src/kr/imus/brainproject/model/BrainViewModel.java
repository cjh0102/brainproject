package kr.imus.brainproject.model;

import java.util.List;

import com.hipits.apps.entertain.brainproject.manager.FontSizeManager;

import android.graphics.Bitmap;

public class BrainViewModel {
	
	private int num;
	private Bitmap bitmap;

	private List<String> items;
	private List<Point> points;
	private List<Integer> colors;
	private List<Float> fonts;
	
	public BrainViewModel (int num, List<String> items, List<Float> fonts,List<Point> point, List<Integer> color, Bitmap bitmap) {
		this.num = num;
		this.items = items;
		this.points = point;
		this.colors = color;
		this.bitmap = bitmap;
		this.fonts = fonts;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public List<Integer> getColors() {
		return colors;
	}

	public void setColors(List<Integer> colors) {
		this.colors = colors;
	}

	public List<Float> getFonts() {
		return fonts;
	}


	public void setFonts(List<Float> fonts) {
		this.fonts = fonts;
	}
}
