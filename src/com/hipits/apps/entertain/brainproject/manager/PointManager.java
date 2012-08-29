package com.hipits.apps.entertain.brainproject.manager;

import java.util.ArrayList;
import java.util.List;

import kr.imus.brainproject.model.Point;

public class PointManager {
	private static final Point[][] pointArray = new Point[][] {
		{new Point (52, 35)},
		{new Point (34, 36), new Point (67, 36)},
		{new Point (34, 36), new Point (67, 33), new Point (50, 45)},
		{new Point (34, 36), new Point (65, 33), new Point (50, 45), new Point (80, 43)},
		{new Point (33, 36), new Point (67, 33), new Point (50, 45), new Point (80, 43), new Point (45, 26)},
		{new Point (30, 36), new Point (72, 32), new Point (50, 45), new Point (80, 43), new Point (45, 26), new Point (53, 35)},
		{new Point (31, 36), new Point (75, 39), new Point (50, 45), new Point (76, 48), new Point (45, 26), new Point (53, 35), new Point (68, 27)},
		{new Point (31, 36), new Point (75, 39), new Point (50, 45), new Point (76, 48), new Point (45, 26), new Point (53, 35), new Point (70, 29), new Point (48, 56)},
	};
	
	private static PointManager pointManager;
	
	private PointManager(){
		
	}
	
	public static PointManager getInstance () {
		if (pointManager == null) {
			pointManager = new PointManager();
		}
		
		return pointManager;
	}
	
	public List<Point> getPoint (int num) {
		List <Point> points= new ArrayList<Point> ();
		num = num - 1;
		for (int i = 0; i < pointArray[num].length; i++){
			points.add(pointArray[num][i]);
		}
		
		return points;
	}
}
