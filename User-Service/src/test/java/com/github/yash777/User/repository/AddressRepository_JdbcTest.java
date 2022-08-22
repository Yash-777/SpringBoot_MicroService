package com.github.yash777.User.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.yash777.User.entity.AddressTableEntity;
import com.github.yash777.User.repository.h2.AddressRepository;
import com.github.yash777.User.repository.h2.AddressRepositoryImpl;

@RunWith(SpringRunner.class) @ActiveProfiles("test")
@DataJdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestPropertySource(
		locations = "classpath:db-test.properties"
		)
public class AddressRepository_JdbcTest { // ERROR
	@Autowired MockAddressRepositoryImpl repo;
	//org.springframework.data.jdbc.repository.config.Ab
	@org.junit.jupiter.api.Test
	public void getCurrentAddressTest() {
		AddressTableEntity expected = AddressTableEntity.builder().userUniqueId("GITHUB-777")
		.country("India").state("TS").city("HYD")
		.build();
		
		AddressTableEntity currentAddress = repo.getCurrentAddress("Git");
		
		assertEquals(expected, currentAddress);
	}
}
