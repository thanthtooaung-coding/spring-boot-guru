package com.alvin.springbootguru;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestGuruApplication.class)
@ActiveProfiles("test")
class SpringBootGuruApplicationTests {

	@Test
	void contextLoads() {
	}
}
