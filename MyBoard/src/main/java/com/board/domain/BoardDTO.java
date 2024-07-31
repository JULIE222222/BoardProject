package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO extends CommonDTO {
	
	private Long idx;
	private String title;
	private String content;
	private String writer;
	private int viewCnt;
	private String noticeYn;
	private String secretYn;
	private String deleteYn;
	private LocalDateTime insertTime;
	private LocalDateTime updateTime;
	private LocalDateTime deleteTime;
	
	
	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", viewCnt=" + viewCnt + ", noticeYn=" + noticeYn + ", secretYn=" + secretYn + ", deleteYn="
				+ deleteYn + ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", deleteTime=" + deleteTime
				+ "]";
	}
	
	
}
