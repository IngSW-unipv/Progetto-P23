package it.unipv.ingsfw.exception;

import java.io.PrintStream;

public class WrongPasswordException extends Exception {

	public WrongPasswordException(PrintStream os) {
		os.println("login denied");
	}

	

}
