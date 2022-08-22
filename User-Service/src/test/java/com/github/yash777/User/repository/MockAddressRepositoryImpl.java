package com.github.yash777.User.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.github.yash777.User.entity.AddressTableEntity;
import com.github.yash777.User.repository.h2.AddressRepository;

@Repository @Profile("test")
public class MockAddressRepositoryImpl implements AddressRepository {

	public AddressTableEntity getCurrentAddress(String userUniqueId) {
		if (userUniqueId == null) {
			return AddressTableEntity.builder()
					.country("India").state("TSG").city("HYD")
					.build();
		} else {
			return AddressTableEntity.builder().userUniqueId("GITHUB-777")
					.country("India").state("TS").city("HYD")
					.build();
		}
	}
	
	public AddressTableEntity saveAddress(AddressTableEntity entity) {
		if (entity.getCity().equals(null)) {
			return AddressTableEntity.builder()
					.country("India").state("TSC").city("HYD")
					.build();
		} else {
			return getCurrentAddress(entity.getUserUniqueId());
		}
	}
	
}
