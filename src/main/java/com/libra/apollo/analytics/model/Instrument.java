package com.libra.apollo.analytics.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.libra.apollo.analytics.model.enums.InstrumentStatus;
import com.libra.apollo.analytics.model.enums.StockType;

@SuppressWarnings("serial")
@Entity
@Table(name="stocks")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Instrument extends DomainObject<Long> {

	protected String name;
	protected String ticker;
	protected String bloombergId;
	protected String bloombergGlobalId;
	protected String factsetId;
	protected String cusip;
	protected String isin;
	protected String reuters;
	protected String sedol;
	protected Boolean disabled = Boolean.FALSE;
	protected StockType type;
	protected StatusMessage statusMessage;
	private InstrumentStatus status;
//	private Region region;
//	protected Exchange exchange;
//	protected Currency currency;
//	protected TradingDays tradingDays;
	protected Boolean autoUpdate = Boolean.TRUE;
}
