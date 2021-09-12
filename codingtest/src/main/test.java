package main;

import java.util.ArrayList;
import java.util.HashMap;

public class test {

	public static void main(String[] args) {
		
		int gameCount;
		InputView inputview = new InputView();
		ResultView resultview = new ResultView();
		Grade grade;

		
		inputview.buyMoney(); // 구입금액 입력
		gameCount = inputview.game(); // 구입금액에 따른 게임횟수
		HashMap<Integer, int[]> lottoNum = resultview.buyLotto(gameCount); // 게임횟수를 매개변수로 가져와 로또 구매
		HashMap<Integer, int[]> resultWinNum = inputview.inputWinNum(); // 당첨번호 입력 
		grade = new Grade(resultWinNum,lottoNum);
		grade.rank();
		
	}


}
