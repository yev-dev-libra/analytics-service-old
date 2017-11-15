package com.libra.apollo.analytics.repository.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.Condition;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class LibraStockIndicatorSpecification<T extends LibraStockIndicator> {

	
	public Specification<T> conditions(final List<Condition> conditions){
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		
	}
	

}
