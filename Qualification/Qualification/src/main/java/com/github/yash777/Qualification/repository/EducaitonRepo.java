package com.github.yash777.Qualification.repository;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.yash777.Qualification.entity.Education;

@Repository
@Transactional
public class EducaitonRepo {
	@Autowired
	private org.hibernate.SessionFactory factory;
	
	public boolean save(Education educaiton) {
		boolean status = false;
		try {
			Session currentSession = factory.getCurrentSession();
			currentSession.save(educaiton);
			status = true;
		} catch (Exception e) {
			System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Education getUserEducaitonDetails(String userUniqueId) {
		Session currentSession = factory.getCurrentSession();
		//currentSession.findByUserUniqueId(userUniqueId);
		Query<Education> query = 
				//currentSession.createQuery("from Education u where u.user_unique_id=:param1", Education.class);
		// org.hibernate.QueryException: could not resolve property: user_unique_id of: com.github.yash777.Qualification.entity.Education
				currentSession.createQuery("from Education u where u.userUniqueId=:param1", Education.class);
		query.setParameter("param1", userUniqueId);
		Education user = query.uniqueResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public Education updateUserEducaitonDetails(Education ed) {
		String userUniqueId = ed.getUserUniqueId();
		if(userUniqueId == null || userUniqueId.isEmpty()) {
			return Education.builder()
					.qualificaiton("User Details not found")
					.build();
		}
		
		Session currentSession = factory.getCurrentSession();
		//Transaction tx = currentSession.beginTransaction();
		Query<Education> query = 
			//currentSession.createQuery("update Education u set u.specialization = :param2 where u.userUniqueId=:param1", Education.class);
				//java.lang.IllegalArgumentException: Update/delete queries cannot be typed
			currentSession.createQuery("update Education u set u.specialization = :param2 where u.userUniqueId=:param1");
			//https://coderanch.com/t/676530/frameworks/Spring-JPA-Hibernate-java-lang
		query.setParameter("param1", userUniqueId);
		query.setParameter("param2", ed.getSpecialization());
		int user = query.executeUpdate();
		System.out.println("Update Status:"+user);
		return getUserEducaitonDetails(userUniqueId);
		// tx.commit(); session.close();
	}
	
	//JPA on calling this function - findByName(String name) JPA will create Query SELECT * FROM employee WHERE name="employee name";
	
}
