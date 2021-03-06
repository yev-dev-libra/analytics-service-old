package com.libra.apollo.analytics.entity.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.annotations.TypeDef;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@TypeDef(defaultForType = CustomBigDecimal.class, typeClass = CustomBigDecimal.class)
public class CustomBigDecimal implements UserType {

	public static final String HIBERNATE_TYPE_NAME = "com.libra.apollo.analytics.entity.type.CustomBigDecimal";
	private static final Logger log = LoggerFactory.getLogger(CustomBigDecimal.class);
	
	@Override
	public int[] sqlTypes() {
		return new int[] { Types.DECIMAL };
	}

	@Override
	public Class<CustomBigDecimal> returnedClass() {
		return CustomBigDecimal.class;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		resultSet.getBigDecimal(names[0]);
		if (resultSet.wasNull()) {
			return null;
		}
		return resultSet.getBigDecimal(names[0]);
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value instanceof Double) {
			final BigDecimal doubleValue = BigDecimal.valueOf((Double)value);
			if (doubleValue == null || Double.valueOf(doubleValue.doubleValue()).isInfinite() || Double.valueOf(doubleValue.doubleValue()).isNaN()) {

				if (doubleValue != null && Double.valueOf(doubleValue.doubleValue()).isInfinite()) {
					log.warn("Sending an infinitevalue to the database is not supported." + "\n.Statement = '" + statement + "' valueIndex = '" + index + "'.");
				}

				statement.setNull(index, Types.DOUBLE);
			} else {
				statement.setBigDecimal(index, doubleValue);
			}
		} else {
			statement.setObject(index, value);
		}
		
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(final Object thisOne, final Object thatOne) throws HibernateException {
		if (thisOne == thatOne)
			return true;
		if (thisOne == null || thatOne == null)
			return false;
		return thisOne.equals(thatOne);
	}

	@Override
	public int hashCode(final Object value) throws HibernateException {
		return value.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}
}
