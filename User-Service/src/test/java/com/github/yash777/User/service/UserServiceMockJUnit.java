package com.github.yash777.User.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.yash777.User.entity.UserTable;
import com.github.yash777.User.repository.h2.UserRepository;

/*
List mockList = Mockito.mock(ArrayList.class);
-> @Mock List<String> mockedList;
 * */
@RunWith(MockitoJUnitRunner.class) //- @org.mockito.Mock

// java.lang.NullPointerException: Cannot invoke "com.github.yash777.User.repository.UserRepository.findAll()" because "this.repo" is null
//at com.github.yash777.User.service.UserServiceMockJUnit.getUserWithNoData(UserServiceMockJUnit.java:41)
@ExtendWith(MockitoExtension.class)
public class UserServiceMockJUnit {

	@Mock public UserRepository repo;
	@InjectMocks public UserService userService;
	
	@org.junit.jupiter.api.Test
	public void getUserWithNoData() {
		System.out.println("No Data Test");
		
		UserTable user = UserTable.builder().firstName("Yash").build();
		List<UserTable> emptyList = new ArrayList<>();
		emptyList.add(user);
		Mockito.when(repo.findAll()).thenReturn( emptyList );
		
		List<UserTable> users = userService.getUsers();
		
		//assertEquals(0, users.size());
		assertEquals(1, users.size());
		System.out.println("No Data Test Completed");
	}
	
	
}
