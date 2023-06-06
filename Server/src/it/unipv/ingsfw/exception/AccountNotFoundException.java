package it.unipv.ingsfw.exception;

import java.io.PrintStream;

public class AccountNotFoundException extends Exception {

	public AccountNotFoundException(PrintStream os) {
		os.println("login denied");
	}

}
