package com.health.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareTradeDto {
	
	private int shareTradeNum;
	private String shareTradeId;
	private String shareTradeCategory;
	private String shareTradeNick;
	private String shareTradeTitle;
	private String shareTradeContent;
	private String shareTradeRegion;
	private String shareTradeTel;
	private int shareTradeMoney;
	private int shareTradeHit;
	private String shareTradeFile;
	private Timestamp shareTradeDate;
}
