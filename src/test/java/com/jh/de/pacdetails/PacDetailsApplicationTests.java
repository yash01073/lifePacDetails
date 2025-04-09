package com.jh.de.pacdetails;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PacDetailsApplicationTests extends BaseTest {

	@Autowired
	private Environment env;

	//@Test
	void contextLoads() {
		List<String> list = Arrays.stream(env.getActiveProfiles()).collect(Collectors.toList());
		assertEquals("unittest", list.get(0), "Profile does match");
	}

}
