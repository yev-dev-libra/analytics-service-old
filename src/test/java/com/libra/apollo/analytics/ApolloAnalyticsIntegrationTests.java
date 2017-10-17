package com.libra.apollo.analytics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {
////		ApolloAnalyticsApplicationTests.class, 
//		H2JpaConfigWithProfile.class})
//		@ActiveProfiles("test")
//public class ApolloAnalyticsIntegrationTests {
//
//}

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {H2JpaConfig.class})
public class ApolloAnalyticsIntegrationTests{
	@Test
    public void exampleTest() {
    }
}