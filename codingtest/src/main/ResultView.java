package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ResultView {

	Lotto lotto = new Lotto();
	InputView inputview = new InputView();
	HashMap<Integer, int[]> hashmap = new HashMap<Integer, int[]>();
	int count = 0;

	
	public HashMap<Integer, int[]> buyLotto(int gameCount) {
		for (int i = 0; i < gameCount; i++) {
			hashmap.put(i,lotto.playLotto());
		System.out.println(Arrays.toString(hashmap.get(i)));
		}
		
		return hashmap;
	}

}
