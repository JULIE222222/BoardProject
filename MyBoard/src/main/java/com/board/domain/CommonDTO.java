package com.board.domain;

import java.time.LocalDateTime;

import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {
	
	private PaginationInfo paginationInfo;
	private String deleteYn;
	private LocalDateTime insertTime;
	private LocalDateTime updateTime;
	private LocalDateTime deleteTime;
	public PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}
	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	
	@Override
	public String toString() {
		return "CommonDTO [paginationInfo=" + paginationInfo + ", deleteYn=" + deleteYn + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", deleteTime=" + deleteTime + "]";
	}
	
	
	
	

}
