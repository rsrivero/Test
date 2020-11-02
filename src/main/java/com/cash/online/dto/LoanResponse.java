package com.cash.online.dto;

import java.util.List;

public class LoanResponse {

	private List<LoanDTO> items;
	
	private Paging paging;

	public List<LoanDTO> getItems() {
		return items;
	}

	public void setItems(List<LoanDTO> items) {
		this.items = items;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
