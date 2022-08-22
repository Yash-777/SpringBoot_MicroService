package com.github.yash777.User.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.yash777.User.controller.Home;

//@SpringBootTest
// https://spring.io/guides/gs/testing-web/
public class SmokeTest {
// Use Power-Mock to test static, private and final methods.
	@Autowired private Home controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}