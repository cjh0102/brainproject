package com.hipits.apps.entertain.brainproject.manager;

import kr.imus.brainproject.activity.StartActivity;

public class BrainManager {
	private int curWidth;
	private int curHeight;

	private static BrainManager brainManager;

	public static BrainManager getInstance() {

		if (brainManager == null) {
			brainManager = new BrainManager ();
		}
		return brainManager;
	}

	private BrainManager () {
		this.curWidth = StartActivity.curWidth;
		this.curHeight = StartActivity.curHeight;
	}

	public int getCurWidth () {
		return curWidth;
	}

	public int getCurHeight () {
		return curHeight;
	}

	public float calX(float x) {
		return curWidth * (x / 100.0f);
	}

	public float calY (float y) {
		return curHeight * (y / 100.0f);
	}
}
