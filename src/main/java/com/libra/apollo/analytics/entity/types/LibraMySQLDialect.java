package com.libra.apollo.analytics.entity.types;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import java.sql.Types;

import org.hibernate.type.StandardBasicTypes;

public class LibraMySQLDialect extends MySQL5InnoDBDialect {

	public LibraMySQLDialect() {
		super();
		registerColumnType(Types.DECIMAL, StandardBasicTypes.BIG_DECIMAL.getName());
		registerHibernateType(Types.DECIMAL, StandardBasicTypes.BIG_DECIMAL.getName());
	}

}
