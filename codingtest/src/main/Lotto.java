package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lotto {

	public int[] playLotto() {
		ArrayList<Integer> allLottoNum = new ArrayList<Integer>();
		for (int i = 1; i < 45; i++) {
			allLottoNum.add(i);
		}
		Collections.shuffle(allLottoNum);

		int[] randNum = new int[6];
		for (int i = 0; i < 6; i++) {
			randNum[i] = allLottoNum.get(i);
		}
		// 정렬하기
		Arrays.sort(randNum);

		return randNum;
	}

}
