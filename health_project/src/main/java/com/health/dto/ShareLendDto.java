package com.health.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareLendDto {
	
	private int shareLendNum;
	private String shareLendId;
	private String shareLendCategory;
	private String shareLendNick;
	private String shareLendTitle;
	private String shareLendContent;
	private String shareLendRegion;
	private String shareLendTel;
	private int shareLendMoney;
	private int shareLendHit;
	private String shareLendFile;
	private Timestamp shareLendDate;
	
	
}
