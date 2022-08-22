package com.github.yash777.User.repository.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.yash777.User.entity.AddressTableEntity;

@Repository @Profile("!test") 
//@Transactional
public class AddressRepositoryImpl implements AddressRepository {

	// Spring’s JdbcTemplate and NamedParameterJdbcTemplate classes are auto-configured, and you can @Autowire them directly into your own beans
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	// org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
	public AddressTableEntity getCurrentAddress(String userUniqueId) {
		String query = "SELECT * FROM address WHERE user_unique_id=? and PRESENT=1";
		try {
			AddressTableEntity addr = jdbcTemplate.queryForObject(query, new Object[]{userUniqueId},
					//new AddressTableEntityMapper());
					new BeanPropertyRowMapper<>(AddressTableEntity.class));
			return addr;
		} catch(EmptyResultDataAccessException e) {
			return AddressTableEntity.builder().build();
		}
	}
	/*
	private class AddressTableEntityMapper implements RowMapper<AddressTableEntity> {
		//private final Logger log;
		//public EmployeeMapper(final Logger log) { this.log = log; }
		@Override
		public AddressTableEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return AddressTableEntity.builder()
					//.unitId(rs.getObject("unitId", Long.class))
					.build();
		}
	}
	
	public AddressTableEntity getCurrentAddress_JT(String userUniqueId) {
		
		try {
			String query = "SELECT * FROM address WHERE user_unique_id=? and PRESENT=1";
			
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement( query );
			pstmt.setString(1, userUniqueId );
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return AddressTableEntity.builder().build();
			}
		} catch(EmptyResultDataAccessException | SQLException e) {
		}
		return AddressTableEntity.builder().build();
	}
	*/
	
	
	// https://stackoverflow.com/a/48322371/5081877
	public AddressTableEntity saveAddress(AddressTableEntity entity) {
		// org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: NULL not allowed for column "USER_UNIQUE_ID"; SQL statement:
		String insertQuery = "INSERT INTO address (USER_UNIQUE_ID, COUNTRY, STATE, CITY, FULLADDRESS, PRESENT) VALUES (?, ?,?,?,?,?)";
		Object[] params = new Object[] {entity.getUserUniqueId(), entity.getCountry(), entity.getState(), entity.getCity(),
				entity.getFullAddress(), entity.getPresent()};
		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		int update = jdbcTemplate.update(insertQuery, params, types);
		
		if (update == 1) {
			System.out.println("Data insertion success.");
			AddressTableEntity currentAddress = getCurrentAddress(entity.getUserUniqueId());
			System.out.println( currentAddress );
			return currentAddress;
		} else {
			System.out.println("Data insertion failure!");
			return AddressTableEntity.builder().build();
		}
	}
}
