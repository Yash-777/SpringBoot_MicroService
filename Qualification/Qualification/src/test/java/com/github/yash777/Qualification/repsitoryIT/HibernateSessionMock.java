package com.github.yash777.Qualification.repsitoryIT;

import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionRegistry;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;

//@RunWith(org.mockito.runners.MockitoJUnitRunner.class) // https://gist.github.com/yclian/1210790/5948ebd45d359abb7a2b919df934bdd15930220c
public abstract class HibernateSessionMock {
//	SessionFactory sf;
//	org.hibernate.engine.SessionFactoryImplementor sfi;
//	@Mock
//	org.hibernate.classic.Session s;
//	@Mock
//	HibernateTemplate t;
//
//	@Before
//	public void setUp() throws Exception {
//		sf = mock(SessionFactory.class, withSettings().extraInterfaces(org.hibernate.engine.SessionFactoryImplementor.class));
//		sfi = (SessionFactoryImplementor) sf;
//		when(sf.openSession()).thenReturn(s);
//		when(sfi.getSqlFunctionRegistry()).thenReturn(new SQLFunctionRegistry(new MySQL5Dialect(), new HashMap<Object, Object>()));
//	}
}
