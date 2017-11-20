package com.elsevier.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/*
 * Model object used to display as response
 */
public class FileSearchBO {

	private int count;
	
	private String query;
	
	private List<String> fileNames;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<String> getFileNames() {
		if (CollectionUtils.isEmpty(fileNames)) {
			fileNames = new ArrayList<>();
		}
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
}
