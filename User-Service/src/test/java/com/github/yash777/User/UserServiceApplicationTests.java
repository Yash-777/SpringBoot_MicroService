package com.github.yash777.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
		String obj1 = "Junit4", obj2 = "Junit4";
		assertEquals(obj1, obj2);
	}

}
