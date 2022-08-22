package com.github.yash777.User.repository.h2;

import com.github.yash777.User.entity.AddressTableEntity;

public interface AddressRepository {
	public AddressTableEntity getCurrentAddress(String userUniqueId);
	public AddressTableEntity saveAddress(AddressTableEntity entity);
}
