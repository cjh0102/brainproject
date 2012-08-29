package com.hipits.apps.entertain.brainproject.manager;

import java.util.ArrayList;
import java.util.List;

public class FontSizeManager {
	
	private final float fontSizes[][] = {
			{9},
			{4, 5},
			{4, 5, 3},
			{4, 5, 3.9f, 2.8f},
			{4, 5, 3.5f, 2.6f, 2.5f},
			{3.5f, 3.4f, 3.5f, 2.6f, 3.4f, 3.4f},
			{3.5f, 3.4f, 4.6f, 3.4f, 3.4f, 3.2f, 3.4f},
			{3.5f, 3.4f, 4.6f, 3.4f, 3.4f, 3.5f, 3.4f, 3.3f}
			
	};
	private static FontSizeManager fontSizeManager;
	
	public static FontSizeManager getInstance() {
		if (fontSizeManager == null) {
			fontSizeManager = new FontSizeManager();
		}
		return fontSizeManager;
	}
	
	public List<Float> getFontSizes(int num) {
		int index = num - 1;
		List<Float> fontSizesList = new ArrayList<Float>();
		for (int i = 0; i < fontSizes[index].length; i++) {
			fontSizesList.add(fontSizes[index][i]);
		}
		return fontSizesList;
	}
}
