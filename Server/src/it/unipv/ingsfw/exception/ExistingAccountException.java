package it.unipv.ingsfw.exception;

import java.io.PrintStream;

public class ExistingAccountException extends Exception{

	public ExistingAccountException(PrintStream os) {
		os.println("account already exist");
		
	}

}
