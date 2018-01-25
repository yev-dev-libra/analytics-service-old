package com.libra.apollo.analytics;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.libra.apollo.analytics.config.ApolloAnalyticsJpaConfogiration;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Import({
	ApolloAnalyticsJpaConfogiration.class
})
public abstract class AbstractRepositoryTest {

}
