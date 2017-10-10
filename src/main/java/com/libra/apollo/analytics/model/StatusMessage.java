package com.libra.apollo.analytics.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.libra.apollo.analytics.model.enums.MessageSeverity;

@Embeddable
public class StatusMessage implements Serializable {
	
private static final long serialVersionUID = -197376228338875494L;
	
	private String text;
	private Date lastUpdate;
	private MessageSeverity severity;
	
	//@SuppressWarnings("unused")
	public StatusMessage(){};// Private constructor for hibernate
	public StatusMessage(String text, Date lastUpdate, MessageSeverity severity){
		this.text = text;
		this.lastUpdate = lastUpdate;
		this.severity = severity;
	}
	
	/**
	 * @return the text
	 */
	@Column(name = "status_message_text", nullable = true)
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the serverity
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "status_message_severity", nullable = true)
	public MessageSeverity getSeverity() {
		return severity;
	}
	/**
	 * @param serverity the serverity to set
	 */
	public void setSeverity(MessageSeverity serverity) {
		this.severity = serverity;
	}
	
	/**
	 * @return the lastUpdate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "status_message_last_update", nullable = true)
	public Date getLastUpdate() {
		return lastUpdate;
	}
	
	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}