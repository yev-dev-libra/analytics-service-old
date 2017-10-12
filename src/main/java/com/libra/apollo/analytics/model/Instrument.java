package com.libra.apollo.analytics.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import com.libra.apollo.analytics.model.enums.InstrumentStatus;
import com.libra.apollo.analytics.model.enums.StockType;

//@NamedQueries({@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findAll", query="select s from Instrument as s"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.countStocksByType", query="select s from Instrument as s where s.type = :stockTypeId"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findByTicker", query="select s from Instrument as s where s.ticker = :ticker"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findByGroup", query="select s from Instrument as s inner join s.groups as group where group.id = :groupId"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findByBloombergId", query="select s from Instrument as s where s.bloombergId = :bloombergId"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findByIsin", query="select s from Instrument as s where s.isin = :isin"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findByIsinAndExchange", query="select s from Instrument as s where s.isin = :isin and s.exchange.code = :exchangeCode"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.findBySedol", query="select s from Instrument as s where s.sedol = :sedol"), 
//	@NamedQuery(name="com.libra.apollo.persistence.model.Instrument.checkUniqueConstraints", query="select count(s.id) from Instrument as s where s.bloombergId = :id1 or s.sedol = :id2 or s.isin = :id3")})
//@SuppressWarnings("serial")
//@Entity
//@Table(name="stocks")
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
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
