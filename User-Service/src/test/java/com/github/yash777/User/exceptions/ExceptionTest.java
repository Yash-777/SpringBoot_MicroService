package com.github.yash777.User.exceptions;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.yash777.User.exception.FileNotFoundExceptionCustom;

@RunWith(SpringRunner.class)
//@SpringBootTest // @BootstrapWith(SpringBootTestContextBootstrapper.class)
public class ExceptionTest {

	//@Mock FileNotFoundExceptionCustom custom;
	
	// @org.junit.Test(expected = FileNotFoundExceptionCustom.class)
	//@org.junit.jupiter.api.Test
	public void fileException() throws FileNotFoundExceptionCustom {
		throw new FileNotFoundExceptionCustom("Custom File Exception");
	}
	
	@org.junit.jupiter.api.Test
	protected <T extends Throwable> void assertExpectedException() {
        String expectedMessage = "Custom File Exception";
        
	    FileNotFoundExceptionCustom thrown = assertThrows(
	    		FileNotFoundExceptionCustom.class,
	            () -> FileNotFoundExceptionCustom.throwException(), expectedMessage
	     );

	     assertTrue(thrown.getMessage().contains("Custom"));
	}
}
