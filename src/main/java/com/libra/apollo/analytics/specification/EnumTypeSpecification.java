package com.libra.apollo.analytics.specification;

import org.springframework.data.jpa.domain.Specification;

/*
 * Generic interface for InstrumentDataField types (e.g. stampDate)
 */
public interface EnumTypeSpecification<T,E> extends Specification<T>  {

}
