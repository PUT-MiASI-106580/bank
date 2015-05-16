package pl.put.miasi.bank.accountdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.put.miasi.bank.commondata.Owner;

public class AccountsTest {
	
	Accounts accounts;
	Owner accountOwner;

	@Before
	public void setUp() throws Exception {
		accounts = new Accounts();
		accountOwner = new Owner();
	}

	@Test
	public void testCreateAccount() {
		String returned = accounts.createAccount(accountOwner);
		assertTrue(accounts.accountExists(returned));
	}

	@Test
	public void testChangeAccountType() {
		fail("Not yet implemented");
	}

}
