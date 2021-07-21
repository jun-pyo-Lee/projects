package com.health.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.health.dto.ShareLendDto;
import com.health.dto.ShareTradeDto;

public interface ShareService {
	// 구매
	List<ShareTradeDto> buyList();
	
	// 판매
	List<ShareTradeDto> sellList();
	
	//빌려줌
	List<ShareLendDto> lendList();
	
	//빌려봄
	List<ShareLendDto> rentList();
		
	// 구매 판매글 작성
	int tradeBoardWrite(ShareTradeDto shareTradeDto, MultipartFile file);
	
	// 빌려봄 빌려줌 글작성
	int lendBoardWrite(ShareLendDto shareLendDto, MultipartFile file);
	
	// 판매 구매 글 보기
	Map<String, Object> tradeBoardContentView(int shareTradeOrder);
	
	// 빌려줌 빌려봄 글 보기
	Map<String, Object> lendBoardContentView(int shareLendOrder);
	
	//찾기 리스트 판매,구매만
	ArrayList<ShareTradeDto> searchList(String searchCondition,String searchKeyword);
	
	
	//구매|판매 글삭제
	int tradeBoardDelete(int shareTradeOrder);
	int tradeBoardUpdate(HttpServletRequest request);
	int lendBoardDelete(int shareLendOrder);
	int lendBoardUpdate(HttpServletRequest request);
	
	
}
