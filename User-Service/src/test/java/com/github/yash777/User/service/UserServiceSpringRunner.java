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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.yash777.User.entity.UserTable;
import com.github.yash777.User.repository.h2.UserRepository;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceSpringRunner {

	@MockBean public UserRepository repo;
	@Autowired public UserService userService;
	
	@org.junit.jupiter.api.Test
	public void getUserWithNoData() {
		System.out.println("No Data Test");
		
		List<UserTable> emptyList = new ArrayList<>();
		emptyList.add( UserTable.builder().build() );
		Mockito.when(repo.findAll()).thenReturn( emptyList );
		
		List<UserTable> users = userService.getUsers();
		
		//assertEquals(0, users.size());
		assertEquals(1, users.size());
		System.out.println("No Data Test Completed");
	}
	
	
}
