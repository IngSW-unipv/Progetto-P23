package it.unipv.ingsfw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import it.unipv.ingsfw.Server;
import it.unipv.ingsfw.classi.User;
import it.unipv.ingsfw.database.UserDAO;
import it.unipv.ingsfw.exception.AccountNotFoundException;
import it.unipv.ingsfw.exception.WrongPasswordException;

public class DatabaseTest {

	@Test
	public void registration() {
		Server server = new Server();
		User user = new User(null);

		try {			
			assertTrue(server.insertUser("user", "password"));

			server.checkLogin("user", "password");
			user = server.getStats("user");

		} catch (AccountNotFoundException e) {		
			e.printStackTrace();
		} catch (WrongPasswordException e) {
			e.printStackTrace();
		}


		assertEquals("user",user.getUsername());

	}
	
	@Test
	public void getStats() {
		Server server = new Server();
		User user = new User(null);
		
		new UserDAO().addWin("user");
		user = server.getStats("user");
		
		assertEquals("1-0-0",user.getWin()+"-"+user.getDraw()+"-"+user.getLose());
	}
	

}
