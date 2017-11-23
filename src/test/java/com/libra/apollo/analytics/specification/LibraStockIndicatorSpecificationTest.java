package com.libra.apollo.analytics.specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.repository.LibraStockIndicatorRepositoryJpa;


@Transactional
public class LibraStockIndicatorSpecificationTest extends AbstractRepositoryTest {

	@PersistenceContext 
	private EntityManager entityManager;
	
	@Autowired 
	private LibraStockIndicatorRepositoryJpa repository;
	
	
}
