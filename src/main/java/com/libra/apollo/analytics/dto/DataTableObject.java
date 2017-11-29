package com.libra.apollo.analytics.dto;

import java.io.Serializable;
import java.util.List;

public class DataTableObject<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3310925003123045426L;

	protected int iTotalRecords;
	protected int iTotalDisplayRecords;
	protected String sEcho;
	protected String sColumns;
	protected List<T> aaData;

	public DataTableObject() {
		super();
	}

	public DataTableObject(List<T> data) {
		this();
		if (data != null && !data.isEmpty()) {
			aaData = data;
			setiTotalRecords(aaData.size());
			setiTotalDisplayRecords(aaData.size());
		}
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

}
