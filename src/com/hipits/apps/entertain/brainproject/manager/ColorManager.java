package com.hipits.apps.entertain.brainproject.manager;

import java.util.ArrayList;
import java.util.List;


public class ColorManager {
	private static final int colorArray[][] = {
	{0xfff4ed7c},
	{0xe4e4a1b3, 0xffffffff},
	{0xfffc4f59, 0xffffffff, 0xffffffff},
	{0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff},
	{0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff},
	{0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff},
	{0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xe4e4a1b3, 0xffffffff, 0xffffffff},
	{0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xfff4ed7c},
	};
	
	private static ColorManager colorManager;
	
	private ColorManager(){}
	
	public static ColorManager getInstance () {
		if (colorManager == null) {
			colorManager = new ColorManager();
		}
		
		return colorManager;
	}
	
	
	public List<Integer> getColor (int num) {
		num = num - 1;
		List <Integer> colors= new ArrayList<Integer> ();
		
		for (int i = 0; i < colorArray[num].length; i++){
			colors.add(colorArray[num][i]);
		}
		return colors;
	}
}
