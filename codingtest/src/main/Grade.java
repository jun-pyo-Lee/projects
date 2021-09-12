package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Grade {
	/**
	 * 그레이드에 받아와야하는것들
		1. 랜덤 로또 번호들 
		2. 당첨번호
		3. 가져와야하고 두개 비교해서 
		4. 맞으면 카운트 증가시키고
		카운트가 몇 일때 3등인지 ~1등인지 카운트 가 또 증가함 (등수별 개별 카운트변수 설정)
		그리고 최종적으로 금액을 합산해서 (등수별로 금액 합산)
		내가 투자한 금액과 비교하고 몇퍼센트인지 확인
	 * 
	 */
	
	int count;
	HashMap<Integer,int[]> winNumber;
	
	HashMap<Integer, int[]> hashmap;
	
	
	Grade(HashMap<Integer, int[]> winNumber,HashMap<Integer, int[]> hashmap){
		this.winNumber = winNumber; //기준
		this.hashmap = hashmap; // 비교대상 로또 랜덤
	}
	public int rank() {
		
		
	
		for(int i=0;i<winNumber.size();i++) {
			System.out.println("리스트 목록"+winNumber.get(i));			
		}//잘됨
		
		for(int i =0;i<hashmap.size();i++) {
			if(winNumber.containsValue(hashmap.get(i))) {
				count++;				
			}
		}
		System.out.println("랭크에서 카운트 수 "+count);
//		for(int i =0;i<hashmap.size();i++) {
//			boolean a = list.contains(hashmap.get(i));
//		}
		return count;


//		
//		for(int i =0;i<hashmap.size();i++) {
//			if(list.contains(hashmap.get(i))) {
//				count++;
//			}
//			
//		}
	}
	
	
	
}
