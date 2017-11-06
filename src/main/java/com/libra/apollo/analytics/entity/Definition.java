package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
	
import lombok.Data;

@Embeddable
@Data 
public class Definition {

	@NotNull
	@Column(name = "code", nullable = false)
	private String code;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = true)
	private String desc;
}
