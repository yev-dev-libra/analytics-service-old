package com.libra.apollo.analytics.api;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(AnalyticsRestController.class)
@TestPropertySource(locations = "classpath:application-test.properties", properties = {})
public class AnalyticsRestControllerTest {

}
