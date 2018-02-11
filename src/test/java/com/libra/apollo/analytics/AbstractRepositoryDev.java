package com.libra.apollo.analytics;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.mapping.Set;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.libra.apollo.analytics.config.ApolloAnalyticsJpaConfogiration;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-dev.properties")
@ActiveProfiles(profiles = "dev")
@Import({
	ApolloAnalyticsJpaConfogiration.class
})
public abstract class AbstractRepositoryDev {

	public static <T> String getTableName(EntityManager em, Class<T> entityClass) {
	    /*
	     * Check if the specified class is present in the metamodel.
	     * Throws IllegalArgumentException if not.
	     */
	    Metamodel meta = em.getMetamodel();
	    

	    
	    EntityType<T> entityType = meta.entity(entityClass);

	    //Check whether @Table annotation is present on the class.
	    Table t = entityClass.getAnnotation(Table.class);

	    String tableName = (t == null)
	                        ? entityType.getName().toUpperCase()
	                        : t.name();
	    return tableName;
	}
}
