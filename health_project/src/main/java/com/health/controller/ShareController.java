package com.health.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.dto.ShareLendDto;
import com.health.dto.ShareTradeDto;
import com.health.service.ShareService;

/**
 * 
 * 해당 프로젝트는 운동코칭매칭용 사이트로, ShareController에서 수행하는 기능으로써는 장비공유 게시판의 장비판매, 구매, 임대,
 * 대여입니다.
 * 
 * @author jun-pyo-Lee
 * @version 0.1
 *
 */
@Controller
public class ShareController {

	@Autowired
	private ShareService shareService;

	/**
	 * 장비 공유 게시판의 메인화면 호출
	 * 
	 * @return
	 */
	@RequestMapping("/shareBoardaccess")
	public String accessShareBoard() {
		return "shareBoard/shareBoard";
	}//ok

	/**
	 * 구매 판매 글 작성창 로드
	 * 
	 * @return shareBoard/writeViewTrade.jsp 호출
	 */
	@RequestMapping(value = "/shareBoard/writeViewTrade.do", method = RequestMethod.POST)
	public String writeViewTrade() {
		return "shareBoard/writeViewTrade";
	}//ok

	/**
	 * 임대/대여 글 작성창 로드
	 * 
	 * @return shareBoard/writeViewLend.jsp 호출
	 */
	@RequestMapping(value = "/shareBoard/writeViewLend.do", method = RequestMethod.POST)
	public String writeViewLend() {
		return "shareBoard/writeViewLend";
	}//ok

	/**
	 * 공유게시판 내에서 판매/구매 탭의 신규 글 작성
	 * 글 작성시 이미지 파일 업로드 처리하며 tradeBoardWrite() 메소드에서 파일 유효화 체크를 진행함
	 * 
	 * @param shareTradeDto
	 * @param imgFile
	 * @param redirectAttr
	 * @return 게시글 작성 성공여부 Message 전달
	 */
	@RequestMapping(value = "/shareBoard/writeTrade.do", method = RequestMethod.POST)
	public String writeTrade(ShareTradeDto shareTradeDto, MultipartFile imgFile, RedirectAttributes redirectAttr) {
		int uploadCheck = shareService.tradeBoardWrite(shareTradeDto, imgFile);
		if (uploadCheck == 1) {
			redirectAttr.addAttribute("msg", "게시글을 작성하였습니다.");
		} else {
			redirectAttr.addAttribute("msg", "게시글 작성에 실패하였습니다.");
		}
		redirectAttr.addAttribute("location", "/shareBoardAccess");
		return "redirect:/util/messageAccess";
	}

	/**
	 * 공유게시판 내에서 임대/대여 탭의 신규 글 작성
	 * 글 작성시 이미지 파일 업로드 처리하며 lendBoardWrite() 메소드에서 파일 유효화 체크를 진행함
	 * 
	 * @param shareLendDto
	 * @param imgFile
	 * @param redirectAttr
	 * @return 게시글 작성 성공여부 Message 전달
	 */
	@RequestMapping(value = "/shareBoard/writeLend.do", method = RequestMethod.POST)
	public String writeLend(ShareLendDto shareLendDto, MultipartFile imgFile, RedirectAttributes redirectAttr) {
		int uploadCheck = shareService.lendBoardWrite(shareLendDto, imgFile);
		if (uploadCheck == 1) {
			redirectAttr.addAttribute("msg", "게시글 작성에 성공하였습니다.");
		} else {
			redirectAttr.addAttribute("msg", "게시글 작성에 실패하였습니다.");
		}
		redirectAttr.addAttribute("location", "/shareBoardAccess");
		return "redirect:/util/messageAccess";

	}//ok

	/**
	 * 공유게시판 내에서 구매/판매 탭에서 제목 클릭 시 해당 글 조회
	 * 
	 * @param shareTradeOrder 조회할 게시글의 sequence 
	 * @param model
	 * @return shareBoard/tradeContentView.jsp 호출
	 */
	@RequestMapping(value = "/shareboard/tradeContentView", method = RequestMethod.POST)
	public String tradeBoardContentView(int shareTradeOrder, Model model) {
		Map<String, Object> tradeContentMap = shareService.tradeBoardContentView(shareTradeOrder);
		model.addAttribute("map", tradeContentMap);

		return "shareBoard/tradeContentView";
	}

	/**
	 * 빌려요 빌려줍니다 글 보기
	 * 
	 * @param shareLendOrder 조회할 게시글의 sequence 
	 * @param model
	 * @return shareBoard/lendContentView.jsp 호출
	 */
	@RequestMapping(value = "/shareboard/lendContentView", method = RequestMethod.POST)
	public String lendBoradContentView(int shareLendOrder, Model model) {
		Map<String, Object> lendContentMap = shareService.lendBoardContentView(shareLendOrder);
		model.addAttribute("map", lendContentMap);
		return "shareBoard/lendContentView";
	}

	/**
	 * 장비 공유 게시판에서 검색
	 * 
	 * @param searchCondition 검색조건 ( 작성자, 지역,제목,내용)
	 * @param searchKeyword 검색단어
	 * @param model
	 * @return shareBoard/searchView.jsp 호출
	 */
	@RequestMapping("/search")
	public String searchList(String searchCondition, String searchKeyword, Model model) {
		ArrayList<ShareTradeDto> searchResultList = shareService.searchList(searchCondition, searchKeyword);
		model.addAttribute("list", searchResultList);
		return "shareBoard/searchView";
	}

	/**
	 * 장비 공유 게시판에서 
	 * 구매/판매 탭 내에 게시글 삭제
	 * 
	 * @param shareTradeOrder 조회할 게시글의 sequence
	 * @param model
	 * @return 게시글 삭제 성공 여부 메세지 호출
	 */
	@RequestMapping(value = "/shareBoard/tradeDelete.do", method = RequestMethod.POST)
	public String shareTradeDelete(int shareTradeOrder, Model model) {
		int resultCheck = shareService.tradeBoardDelete(shareTradeOrder);
		if (resultCheck == 1) {
			model.addAttribute("msg", "게시글 삭제에 성공하였습니다.");
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패하였습니다");
		}
		model.addAttribute("location", "/shareBoardAccess");
		return "util/message";
	}

	/**
	 * 장비 공유 게시판에서 
	 * 임대/대여 탭 내에 게시글 삭제
	 * 
	 * @param shareLendOrder 조회할 게시글의 sequence
	 * @param model
	 * @return 게시글 삭제 성공 여부 메세지 호출
	 */
	@RequestMapping(value = "/shareBoard/lendDelete.do", method = RequestMethod.POST)
	public String shareLendDelete(int shareLendOrder, Model model) {
		int resultCheck = shareService.lendBoardDelete(shareLendOrder);
		if (resultCheck == 1) {
			model.addAttribute("msg", "게시글 삭제에 성공하였습니다.");
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패하였습니다");
		}
		model.addAttribute("location", "/shareBoardAccess");
		return "util/message";
	}

	/**
	 * 장비공유 게시판에서
	 * 판매/구매 탭 게시글 수정
	 * 
	 * @param request 
	 * @param model
	 * @return 게시글 수정 성공 여부 메세지 호출
	 */ 
	@RequestMapping(value = "/shareBoard/tradeUpdate.do", method = RequestMethod.POST)
	public String shareTradeUpdate(HttpServletRequest request, Model model) {
		int resultCheck = shareService.tradeBoardUpdate(request);
		if (resultCheck == 1) {
			model.addAttribute("msg", "게시글을 수정하였습니다.");
		} else {
			model.addAttribute("msg", "게시글 수정에 실패하였습니다");
		}
		model.addAttribute("location", "/shareBoardAccess");

		return "util/message";
	}

	/**
	 * 장비 공유 게시판 에서
	 * 임대/대여 탭 게시글 수정
	 * 
	 * @param request
	 * @param model
	 * @return 게시글 수정 성공 여부 메세지 호출
	 */
	@RequestMapping(value = "/shareBoard/lendUpdate.do", method = RequestMethod.POST)
	public String shareLendUpdate(HttpServletRequest request, Model model) {
		int resultCheck = shareService.lendBoardUpdate(request);
		if (resultCheck == 1) {
			model.addAttribute("msg", "게시글을 수정하였습니다.");
		} else {
			model.addAttribute("msg", "게시글 수정에 실패하였습니다");
		}
		model.addAttribute("location", "/shareBoardAccess");

		return "util/message";
	}

	/**
	 * 장비공유 게시판에서
	 * 각 페이지 클릭 시 
	 * shareBoardCall에서 송신
	 * 값에 따라 웹페이지 호출
	 *  
	 * @param num
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shareBoardCall", method = RequestMethod.POST)
	public String lessonBoardCall(String num, Model model) {
		// 구매 게시판 호출
		if (num.equals("1")) {
			List<ShareTradeDto> tradeList = shareService.buyList();
			model.addAttribute("tradeList", tradeList);
			return "shareBoard/shareBoard01";
			// 판매 게시판 호출
		} else if (num.equals("2")) {
			List<ShareTradeDto> tradeList = shareService.sellList();
			model.addAttribute("tradeList", tradeList);
			return "shareBoard/shareBoard02";
			// 빌려줍니다 게시판 호출
		} else if (num.equals("3")) {
			List<ShareLendDto> lendList = shareService.lendList();
			model.addAttribute("lendList", lendList);
			return "shareBoard/shareBoard03";
			// 빌려봅니다 게시판 호출
		} else if (num.equals("4")) {
			List<ShareLendDto> lendList = shareService.rentList();
			model.addAttribute("lendList", lendList);
			return "shareBoard/shareBoard04";

		} else {
			return "redirect:/";
		}
	}

}
