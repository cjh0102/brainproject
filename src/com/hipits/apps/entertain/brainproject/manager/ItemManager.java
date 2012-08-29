package com.hipits.apps.entertain.brainproject.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ItemManager {
	private static ItemManager itemManager;
	private static final String[] ITEMS = new String[] { "양보", "허겁지겁", "배터리",
		"먹고싶은것", "수면부족", "분노", "낮잠", "불편함", "습관", "관찰력", "예절", "의심", "정의감",
		"데이트코스", "과거", "발목", "상처", "사랑", "몸무게", "더치페이", "외국",
		"놀궁리", "컴퓨터포맷", "정리", "혼자있고싶음", "감수성", "귀찮음" };


	public static ItemManager getInstance (){
		if (itemManager == null){
			itemManager = new ItemManager();
		}
		return itemManager;
	}
	public List<String> getItemList (int numberofItem) {
		Set<String> items = new HashSet<String>();
		Random random = new Random();
		
		while (items.size()!= numberofItem) {
			items.add(ITEMS[random.nextInt(ITEMS.length)]);
		}
		return new ArrayList<String>(items);
	}
}