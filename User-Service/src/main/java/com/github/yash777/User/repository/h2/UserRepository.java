package com.github.yash777.User.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.yash777.User.entity.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long>{
//	Page<City> findAll(Pageable pageable);
//	City findByNameAndStateAllIgnoringCase(String name, String state);
}
