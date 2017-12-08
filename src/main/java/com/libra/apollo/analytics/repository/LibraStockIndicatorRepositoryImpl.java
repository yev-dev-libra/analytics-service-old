package com.libra.apollo.analytics.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.entity.LibraStockIndicator;


@Repository("customLibraStockIndicatorRepository")
@Transactional(readOnly = true)
public class LibraStockIndicatorRepositoryImpl implements CustomLibraStockIndicatorRepository  {

	@PersistenceContext 
	private EntityManager em;
	
//	@Autowired 
//	private LibraStockIndicatorRepository repo;
	
	@Override
	public List<LibraStockIndicator> findAllBySpecification(final Specification<LibraStockIndicator> specification) {
		return null;
	}

}
