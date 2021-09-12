package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class InputView {

	Scanner sc = new Scanner(System.in);
	Lotto lotto = new Lotto();
	int[] winNumber = new int[6];
	final int GAME_MONEY = 1000;
	int money;
	int gameCount;
	int change;

	int buyMoney() {
		System.out.println("구입 금액을 입력 해 주세요.");
		money = sc.nextInt();
		return money;
	}

	public int game() {
		gameCount = money / GAME_MONEY;
		change = money % GAME_MONEY;
		System.out.println(gameCount + "개를 구매했습니다.");
		if (change != 0) {
			System.out.printf("거스름 돈은 %d원 입니다.\n", change);
		}
		return gameCount;
	}

	public HashMap<Integer, int[]> inputWinNum() {
		HashMap<Integer, int[]> hashmap = new HashMap<Integer, int[]>();
		System.out.println("당첨번호를 입력해주세요");
		for (int i = 0; i < winNumber.length; i++) {
			winNumber[i]= sc.nextInt();
			hashmap.put(i, winNumber);
		}
		sc.close();
		return hashmap;
		//System.out.println(Arrays.toString(winNumber));
	}

}
