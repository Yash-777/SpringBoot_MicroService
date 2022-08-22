package com.github.yash777.User.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) @SpringBootTest //@MockBean - Working
// @SpringBootTest // Don’t forget to also add @RunWith(SpringRunner.class) to your test, otherwise the annotations will be ignored
//@RunWith(MockitoJUnitRunner.class) @ExtendWith(MockitoExtension.class) // @Mock - Working
public class UserTableTest {
	@MockBean UserTable userModel; // @RunWith(SpringRunner.class) - @MockBean
	//@Mock UserTable userModel; // @RunWith(MockitoJUnitRunner.class) - @Mock
	
	@org.junit.jupiter.api.Test
	public void toStringTest() {
		
		org.mockito.Mockito 
		.when(userModel.toString())
		.thenReturn("Yash");
		
		assertEquals("Yash", userModel.toString());
	}
	
	@org.junit.jupiter.api.Test
	public void setFirstNameTest() {
		userModel.setFirstName("Yash");
		org.mockito.Mockito 
		.when(userModel.getFirstName())
		.thenReturn("Yash");
		
		assertEquals("Yash", userModel.getFirstName());
	}
}
