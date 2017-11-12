package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class LibraStockIndicatorRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired private LibraStockIndicatorRepository repository;
	
	//TODO
	@Test
	public void shouldBeImmutable() {
		Long stockId = 100L;
		LibraStockIndicator indicator = repository.getOne(1L);//gets only a reference - proxy and does not fetch it from DB
		indicator.setStockId(stockId);
		repository.save(indicator);
		LibraStockIndicator updatedIndicator =  repository.getOne(1L);
		assertNotEquals(updatedIndicator.getStockId(), stockId);
		
	}
	
	@Test
	public void testLibraStockIndicatorFindAll() {
		List<LibraStockIndicator> stockIndicators = repository.findAll();
		assertThat(stockIndicators, hasSize(1));
	}

}
