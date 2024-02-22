package com.sms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagerDTO<T> {

	private List<T> items;
	private int totalPages;
	private int currentPage;
	private long totalElements;
}
