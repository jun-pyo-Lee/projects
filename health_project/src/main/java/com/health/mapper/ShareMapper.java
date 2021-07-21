package com.health.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.health.dto.ShareLendDto;
import com.health.dto.ShareTradeDto;

@Mapper
public interface ShareMapper {
	// 구매
	List<ShareTradeDto> buyListView();

	// 판매
	List<ShareTradeDto> sellListView();

	// 빌려줌
	List<ShareLendDto> lendlistView();

	// 빌려봄
	List<ShareLendDto> rentListView();
	
	

	// 매퍼에서 판매 구매 글작성
	int insertBoardWrite(ShareTradeDto shareTradeDto);

	// 매퍼에서 빌려줌 빌려봄 글작성
	int insertlendBoardWrite(ShareLendDto shareLendDto);

	// 조회수 증가임
	void selectUpHit(String shareTradeNum);

	// 판매 구매 컨텐츠 뷰임
	ShareTradeDto selectTradeBoardContentView(int shareTradeOrder);

	// 빌려줌 빌려봄 컨텐츠 뷰임
	ShareLendDto selectLendBoardContentView(int shareLendOrder);

	// 검색한거 들고오기
	ArrayList<ShareTradeDto> searchList(String searchCondition, String searchKeyword);
	
	// 구매 및 판매 글 삭제
	int tradeBoardDelete(int shareTradeOrder);

	// 구매 및 판매 글 수정
	int tradeBoardUpdate(String shareTradeNum, String shareTradeTitle, String shareTradeRegion,
			String shareTradeMoney, String shareTradeTel, String shareTradeContent);

	// 빌려요 빌려줍니다 글 삭제
	int lendBoardDelete(int shareLendOrder);

	// 빌려요 빌려봅니다 글 삭제
	int lendBoardUpdate(String shareLendNum, String shareLendTitle, String shareLendRegion, String shareLendMoney,
			String shareLendTel, String shareLendContent);

}
