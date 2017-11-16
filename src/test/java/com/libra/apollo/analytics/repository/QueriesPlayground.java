package com.libra.apollo.analytics.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class QueriesPlayground extends AbstractRepositoryTest{

	//can have multiple unit names
	@PersistenceContext 
	private EntityManager entityManager;
	
	@Test
	public void testQueryForInvestmentStylesWithCriteria() {
//		Session session = (Session) entityManager.getDelegate();
//		Criteria criteria = session.createCriteria(InvestmentStyle.class);
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InvestmentStyle> criteriaQuery = criteriaBuilder.createQuery(InvestmentStyle.class); //result class
		Root<InvestmentStyle> root = criteriaQuery.from(InvestmentStyle.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"),criteriaBuilder.parameter(Long.class, "id")));
		
		Query query = entityManager.createQuery(criteriaQuery);
		query.setParameter("id", 1L);
		
		InvestmentStyle style = (InvestmentStyle) query.getSingleResult();
		System.out.println(style);
		
	}
	
	
	
}
