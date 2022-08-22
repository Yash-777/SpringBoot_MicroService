package com.github.yash777.Qualification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.yash777.Qualification.entity.Education;
import com.github.yash777.Qualification.repository.EducaitonRepo;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {
	@Mock public EducaitonRepo repo;
	@InjectMocks public EducationService service;
	
	@org.junit.jupiter.api.Test
	public void saveTest() {
		String uniqueID = "Git777";
		Education education = Education.builder()
				.userUniqueId(uniqueID)
				.build();
		
		Mockito.when(repo.save( education )).thenReturn( true );
		Mockito.when(repo.getUserEducaitonDetails( uniqueID )).thenReturn( education );
		
		Education save = service.save(education);
		assertEquals(education, save);
	}
	
	@org.junit.jupiter.api.Test
	public void updateTest() {
		String uniqueID = "Git777";
		Education education = Education.builder()
				.userUniqueId(uniqueID)
				.build();
		
		Mockito.when(repo.updateUserEducaitonDetails( education )).thenReturn( education );
		Education update = service.update(education);
		assertEquals(education, update);
	}
}
