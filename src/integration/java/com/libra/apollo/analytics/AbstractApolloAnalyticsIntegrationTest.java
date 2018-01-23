package com.libra.apollo.analytics;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles({ "integration", "dev" })
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties", properties = {})
public class AbstractApolloAnalyticsIntegrationTest {


}