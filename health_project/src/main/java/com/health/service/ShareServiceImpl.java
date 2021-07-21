package com.health.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.health.dto.ShareLendDto;
import com.health.dto.ShareTradeDto;
import com.health.mapper.ShareMapper;

/**
 * ShareService상속
 * 
 * @author Administrator
 * @version 0.1
 */
@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	ShareMapper shareMapper;
	List<ShareTradeDto> tradeList;
	List<ShareLendDto> lendList;
	ShareTradeDto shareTradeDto;
	ShareLendDto shareLendDto;

	/**
	 * 구매리스트 보기
	 * 
	 */
	@Override
	public List<ShareTradeDto> buyList() {
		// mapper에서 호출
		tradeList = shareMapper.buyListView();
		return tradeList;
	}

	/**
	 * 판매리스트
	 * 
	 * @return
	 */
	@Override
	public List<ShareTradeDto> sellList() {
		// mapper호출
		tradeList = shareMapper.sellListView();
		return tradeList;
	}

	/**
	 * 빌려줍니다 전체 목록 호출
	 * 
	 */
	@Override
	public List<ShareLendDto> lendList() {
		// mapper호출
		lendList = shareMapper.lendlistView();
		return lendList;
	}

	/**
	 * 빌려봅니다 전체 목록 호출
	 */
	@Override
	public List<ShareLendDto> rentList() {
		lendList = shareMapper.rentListView();
		return lendList;
	}

	/**
	 * 구매 및 판매 내용 보기 1개만
	 * 
	 */
	@Override
	public HashMap<String, Object> tradeBoardContentView(int shareTradeOrder) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		shareTradeDto = shareMapper.selectTradeBoardContentView(shareTradeOrder);
		map.put("sharetradedto", shareTradeDto);
		return map;
	}

	/**
	 * 빌려요 빌려봅니다 내용 보기 1개만
	 * 
	 */
	@Override
	public Map<String, Object> lendBoardContentView(int shareLendOrder) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		shareLendDto = shareMapper.selectLendBoardContentView(shareLendOrder);
		map.put("shareLendDto", shareLendDto);
		return map;
	}

	/**
	 * 구매 및 판매 게시판 글 작성하기
	 * 
	 */
	@Override
	public int tradeBoardWrite(ShareTradeDto shareTradeDto, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
		if (fileNameExtension != "") {
			String fileUrl = "C:/workspace/health_project/src/main/resources/static/img/uploadShare/";
			String uploadFileName = RandomStringUtils.randomAlphanumeric(8) + "." + fileNameExtension;
			File f = new File(fileUrl + uploadFileName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			shareTradeDto.setShareTradeFile(uploadFileName);
		} else {
			shareTradeDto.setShareTradeFile("");
		}
		int i = shareMapper.insertBoardWrite(shareTradeDto);
		return i;
	}

	/**
	 * 빌려요 빌려봅니다 게시판 글 작성하기
	 * 
	 */
	@Override
	public int lendBoardWrite(ShareLendDto shareLendDto, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
		if (fileNameExtension != "") {
			String fileUrl = "C:/workspace/health_project/src/main/resources/static/img/uploadShare/";
			String uploadFileName = RandomStringUtils.randomAlphanumeric(8) + "." + fileNameExtension;
			File f = new File(fileUrl + uploadFileName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			shareLendDto.setShareLendFile(uploadFileName);
		} else {
			shareLendDto.setShareLendFile("");
		}
		int i = shareMapper.insertlendBoardWrite(shareLendDto);
		return i;
	}//check

	/**
	 * 구매 및 판매 검색목록 보기
	 * 
	 */
	@Override
	public ArrayList<ShareTradeDto> searchList(String searchCondition, String searchKeyword) {
		ArrayList<ShareTradeDto> searchResultList = new ArrayList<ShareTradeDto>();
		searchResultList = shareMapper.searchList(searchCondition, searchKeyword);
		return searchResultList;
	}

	/**
	 * 구매 및 판매 글 삭제
	 * 
	 */
	@Override
	public int tradeBoardDelete(int shareTradeOrder) {
		int resultCheck = shareMapper.tradeBoardDelete(shareTradeOrder);
		return resultCheck;
	}

	/**
	 * 빌려요 빌려줍니다 글 삭제
	 * 
	 */
	@Override
	public int lendBoardDelete(int shareLendOrder) {
		int resultCheck = shareMapper.lendBoardDelete(shareLendOrder);
		return resultCheck;
	}

	/**
	 * 구매 및 판매 게시판 글 수정
	 * 
	 */
	@Override
	public int tradeBoardUpdate(HttpServletRequest request) {
		String shareTradeNum = request.getParameter("shareTradeNum");
		String shareTradeTitle = request.getParameter("shareTradeTitle");
		String shareTradeRegion = request.getParameter("shareTradeRegion");
		String shareTradeMoney = request.getParameter("shareTradeMoney");
		String shareTradeTel = request.getParameter("shareTradeTel");
		String shareTradeContent = request.getParameter("shareTradeContent");
		int updateResult = shareMapper.tradeBoardUpdate(shareTradeNum, shareTradeTitle, shareTradeRegion, shareTradeMoney,
				shareTradeTel, shareTradeContent);
		return updateResult;
	}

	/**
	 * 빌려요 빌려줍니다 게시판 글 수정
	 * 
	 */
	@Override
	public int lendBoardUpdate(HttpServletRequest request) {
		String shareLendNum = request.getParameter("shareLendNum");
		String shareLendTitle = request.getParameter("shareLendTitle");
		String shareLendRegion = request.getParameter("shareLendRegion");
		String shareLendMoney = request.getParameter("shareLendMoney");
		String shareLendTel = request.getParameter("shareLendTel");
		String shareLendContent = request.getParameter("shareLendContent");
		int updateResult = shareMapper.lendBoardUpdate(shareLendNum, shareLendTitle, shareLendRegion, shareLendMoney, shareLendTel,
				shareLendContent);
		return updateResult;
	}

}
