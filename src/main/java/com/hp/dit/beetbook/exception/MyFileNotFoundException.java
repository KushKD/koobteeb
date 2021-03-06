package com.hp.dit.beetbook.exception;


import com.hp.dit.beetbook.apicontroller.API;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFileNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MyFileNotFoundException.class);

	public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}